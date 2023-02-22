package financial.swap.challenge.client.github;

import financial.swap.challenge.client.github.dto.ContributorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpMethod.GET;

@Component
@RequiredArgsConstructor
public class ContributorWebClient {
    private final RestTemplate restTemplateGithub;
    private final RetryTemplate retryTemplate;

    public CompletableFuture<List<ContributorDto>> findAll(String username, String repository) {
        return CompletableFuture.supplyAsync(() -> retryTemplate.execute(context -> doGet(username, repository)));
    }

    private List<ContributorDto> doGet(String username, String repository) {
        String url = "/{username}/{repository}/contributors";
        ParameterizedTypeReference<List<ContributorDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplateGithub.exchange(url, GET, null, responseType, username, repository).getBody();
    }
}
