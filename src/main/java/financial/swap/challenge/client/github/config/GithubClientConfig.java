package financial.swap.challenge.client.github.config;

import financial.swap.challenge.client.github.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
class GithubClientConfig {
    @Value("${custom.github.base-url}")
    String baseUrlGithub;

    @Bean
    RestTemplate restTemplateGithub(RestTemplateBuilder builder,
                                    AuthInterceptor authInterceptor) {
        return builder.uriTemplateHandler(new DefaultUriBuilderFactory(baseUrlGithub))
                .additionalInterceptors(authInterceptor)
                .build();
    }
}
