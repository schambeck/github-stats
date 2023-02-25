package financial.swap.challenge.client.webhook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
class WebhookClientConfig {
    @Value("${custom.webhook-site.base-url}")
    String baseUrlWebhook;

    @Bean
    RestTemplateBuilder restTemplateBuilderWebhook(RestTemplateBuilderConfigurer configurer) {
        return configurer.configure(new RestTemplateBuilder())
                .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrlWebhook));
    }
}
