package financial.swap.challenge.rabbit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static financial.swap.challenge.util.GithubUtil.createStatsMsg;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatsProducer.class)
@TestPropertySource(locations = "/application.properties")
class StatsProducerTest {
    @Autowired
    private StatsProducer producer;
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void sendMessage() {
        doNothing().when(rabbitTemplate).convertAndSend("stats-exchange", "stats-routing-key", createStatsMsg());

        producer.sendMessage(createStatsMsg());

        verify(rabbitTemplate).convertAndSend("stats-exchange", "stats-routing-key", createStatsMsg());
    }
}
