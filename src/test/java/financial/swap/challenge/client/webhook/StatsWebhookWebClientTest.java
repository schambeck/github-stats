package financial.swap.challenge.client.webhook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

import static financial.swap.challenge.client.github.GithubUtil.createStatsWeb;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(StatsWebhookWebClient.class)
class StatsWebhookWebClientTest {
    @Autowired
    private StatsWebhookWebClient client;

    @MockBean
    private RetryTemplate retryTemplate;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void post() throws JsonProcessingException {
        String statsJson = objectMapper.writeValueAsString(createStatsWeb());
        server.expect(requestTo("/afd6052e-5ef8-4a0a-b82f-d54ae4ed9a7b"))
                .andExpect(method(POST))
                .andExpect(content().json(statsJson))
                .andRespond(withSuccess());
        when(retryTemplate.execute(any(), any(), any()))
                .thenAnswer(invocation -> invocation.<RetryCallback<?, ?>>getArgument(0).doWithRetry(null));

        client.post(createStatsWeb());
    }
}
