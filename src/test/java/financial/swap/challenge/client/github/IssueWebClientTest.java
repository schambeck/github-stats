package financial.swap.challenge.client.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import financial.swap.challenge.client.github.dto.IssueDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static financial.swap.challenge.util.GithubUtil.createIssueDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(IssueWebClient.class)
class IssueWebClientTest {
    @Autowired
    private IssueWebClient client;

    @MockBean
    private RetryTemplate retryTemplate;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll() throws JsonProcessingException {
        String issuesJson = objectMapper.writeValueAsString(List.of(createIssueDto()));
        server.expect(requestTo("/schambeck/api-github/issues"))
                .andRespond(withSuccess(issuesJson, APPLICATION_JSON));
        when(retryTemplate.execute(any(), any(), any()))
                .thenAnswer(invocation -> invocation.<RetryCallback<?, ?>>getArgument(0).doWithRetry(null));

        List<IssueDto> issues = client.findAll("schambeck", "api-github");

        assertThat(issues).hasSameElementsAs(List.of(createIssueDto()));
    }
}
