package financial.swap.challenge.client.github;

import financial.swap.challenge.client.github.dto.ContributorDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class ContributorWebClient {
    private final RestTemplate restTemplateGithub;
    private final RetryTemplate retryTemplate;

    public ContributorWebClient(RestTemplateBuilder restTemplateBuilderGithub, RetryTemplate retryTemplate) {
        this.restTemplateGithub = restTemplateBuilderGithub.build();
        this.retryTemplate = retryTemplate;
    }

    public List<ContributorDto> findAll(String username, String repository) {
        return retryTemplate.execute(context -> doGet(username, repository));
    }

    private List<ContributorDto> doGet(String username, String repository) {
        String url = "/{username}/{repository}/contributors";
        ParameterizedTypeReference<List<ContributorDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplateGithub.exchange(url, GET, null, responseType, username, repository).getBody();
    }
}
