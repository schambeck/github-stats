package financial.swap.challenge.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpServerErrorException;

@Configuration
class RetryConfig {
    @Value("${custom.retry.max-attempts}")
    Integer maxAttempts;
    @Value("${custom.retry.initial-interval}")
    Long initialInterval;
    @Value("${custom.retry.multiplier}")
    Double multiplier;
    @Value("${custom.retry.max-interval}")
    Long maxInterval;
    @Value("${custom.retry.with-random}")
    Boolean withRandom;

    @Bean
    RetryTemplate retryTemplate() {
        return RetryTemplate.builder()
                .maxAttempts(maxAttempts)
                .exponentialBackoff(initialInterval, multiplier, maxInterval, withRandom)
                .retryOn(HttpServerErrorException.class)
                .build();
    }
}
