package financial.swap.challenge.client.webhook;

import financial.swap.challenge.web.StatsWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class StatsWebhookWebClient {
    @Value("${custom.webhook-site.endpoint-id}")
    private String endpointId;
    private final RestTemplate restTemplateWebhook;
    private final RetryTemplate retryTemplate;

    public void post(StatsWeb stats) {
        retryTemplate.execute(context -> doPost(stats));
    }

    private ResponseEntity<Void> doPost(StatsWeb stats) {
        HttpEntity<StatsWeb> requestEntity = new HttpEntity<>(stats, createHeaders());
        return restTemplateWebhook.postForEntity("/{id}", requestEntity, Void.class, endpointId);
    }

    private static HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
