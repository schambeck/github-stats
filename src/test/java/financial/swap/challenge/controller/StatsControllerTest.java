package financial.swap.challenge.controller;

import financial.swap.challenge.rabbit.StatsProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static financial.swap.challenge.util.GithubUtil.createStatsMsg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.ACCEPTED;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatsController.class)
@TestPropertySource(locations = "/application.properties")
class StatsControllerTest {
    @Autowired
    private StatsController controller;
    @MockBean
    private StatsProducer producer;

    @Test
    void create() {
        doNothing().when(producer).sendMessage(createStatsMsg());

        ResponseEntity<Void> response = controller.create();

        assertThat(response.getStatusCode()).isEqualTo(ACCEPTED);
        verify(producer).sendMessage(createStatsMsg());
    }
}
