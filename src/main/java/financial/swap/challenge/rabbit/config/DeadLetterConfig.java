package financial.swap.challenge.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DeadLetterConfig {
    @Value("${dead-letter.queue}")
    String deadLetterQueueName;
    @Value("${dead-letter-exchange}")
    String deadLetterExchangeName;
    @Value("${dead-letter-routing-key}")
    String deadLetterRoutingKey;

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueueName).build();
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(deadLetterExchangeName);
    }

    @Bean
    Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(deadLetterRoutingKey);
    }
}
