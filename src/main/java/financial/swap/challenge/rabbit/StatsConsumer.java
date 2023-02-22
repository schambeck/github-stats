package financial.swap.challenge.rabbit;

import financial.swap.challenge.rabbit.msg.StatsMsg;
import financial.swap.challenge.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StatsConsumer {
    private final StatsService service;

    @RabbitListener(queues = "${stats.queue}")
    public void receiveMessage(StatsMsg msg) {
        service.execute(msg.getUsername(), msg.getRepository());
    }
}
