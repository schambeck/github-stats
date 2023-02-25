package financial.swap.challenge.rabbit;

import financial.swap.challenge.service.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static financial.swap.challenge.util.GithubUtil.createStatsMsg;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatsConsumer.class)
@TestPropertySource(locations = "/application.properties")
class StatsConsumerTest {
    @Autowired
    private StatsConsumer consumer;
    @MockBean
    private StatsService service;

    @Test
    void sendMessage() {
        doNothing().when(service).execute("schambeck", "api-github");

        consumer.receiveMessage(createStatsMsg());

        verify(service).execute("schambeck", "api-github");
    }
}
