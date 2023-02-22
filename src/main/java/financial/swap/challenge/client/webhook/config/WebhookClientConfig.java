package financial.swap.challenge.client.webhook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
class WebhookClientConfig {
    @Value("${custom.webhook-site.base-url}")
    String baseUrlWebhook;

    @Bean
    RestTemplate restTemplateWebhook(RestTemplateBuilder builder) {
        return builder.uriTemplateHandler(new DefaultUriBuilderFactory(baseUrlWebhook))
                .build();
    }
}
