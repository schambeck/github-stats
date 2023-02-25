package financial.swap.challenge.client.github;

import financial.swap.challenge.client.github.dto.ContributorDto;
import financial.swap.challenge.client.github.dto.IssueDto;
import financial.swap.challenge.client.github.mapper.ContributorMapper;
import financial.swap.challenge.client.github.mapper.IssueMapper;
import financial.swap.challenge.web.ContributorWeb;
import financial.swap.challenge.web.StatsWeb;
import financial.swap.challenge.web.IssueWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ConcurrentStatsLoader {
    private final IssueWebClient issueWebClient;
    private final ContributorWebClient contributorWebClient;
    private final IssueMapper issueMapper;
    private final ContributorMapper contributorMapper;

    public StatsWeb execute(String username, String repository) {
        CompletableFuture<List<IssueDto>> issueFuture = CompletableFuture.supplyAsync(() -> issueWebClient.findAll(username, repository));
        CompletableFuture<List<ContributorDto>> contributorFuture = CompletableFuture.supplyAsync(() -> contributorWebClient.findAll(username, repository));
        CompletableFuture.allOf(issueFuture, contributorFuture).join();
        try {
            return createStats(username, repository, issueFuture.get(), contributorFuture.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private StatsWeb createStats(String username,
                                 String repository,
                                 List<IssueDto> issues,
                                 List<ContributorDto> contributors) {
        return StatsWeb.builder()
                .user(username)
                .repository(repository)
                .issues(createIssues(issues))
                .contributors(createContributors(contributors))
                .build();
    }

    private List<IssueWeb> createIssues(List<IssueDto> issues) {
        return issues.stream()
                .map(issueMapper::toWeb)
                .collect(toList());
    }

    private List<ContributorWeb> createContributors(List<ContributorDto> contributors) {
        return contributors.stream()
                .map(contributorMapper::toWeb)
                .collect(toList());
    }
}
