package financial.swap.challenge.rabbit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
class StatsQueueConfig {
    @Value("${stats.queue}")
    String statsQueueName;
    @Value("${stats-exchange}")
    String statsExchangeName;
    @Value("${stats-routing-key}")
    String statsRoutingKey;

    @Value("${dead-letter-exchange}")
    String deadLetterExchangeName;
    @Value("${dead-letter-routing-key}")
    String deadLetterRoutingKey;
    @Value("${x-dead-letter-exchange}")
    String xDeadLetterExchangeName;
    @Value("${x-dead-letter-routing-key}")
    String xDeadLetterRoutingKey;

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory, Jackson2ObjectMapperBuilder builder) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter(builder));
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build().registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    Queue statsQueue() {
        return QueueBuilder.durable(statsQueueName)
                .withArgument(xDeadLetterExchangeName, deadLetterExchangeName)
                .withArgument(xDeadLetterRoutingKey, deadLetterRoutingKey)
                .build();
    }

    @Bean
    DirectExchange statsExchange() {
        return new DirectExchange(statsExchangeName);
    }

    @Bean
    Binding statsBinding(Queue statsQueue, DirectExchange statsExchange) {
        return BindingBuilder.bind(statsQueue).to(statsExchange).with(statsRoutingKey);
    }
}
