package financial.swap.challenge.rabbit;

import financial.swap.challenge.rabbit.msg.StatsMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatsProducer {
    private final RabbitTemplate template;
    @Value("${stats-exchange}")
    private String statsExchange;
    @Value("${stats-routing-key}")
    private String statsRoutingKey;

    public void sendMessage(StatsMsg msg) {
        template.convertAndSend(statsExchange, statsRoutingKey, msg);
    }
}
