package financial.swap.challenge.client.github;

import financial.swap.challenge.client.github.dto.ContributorDto;
import financial.swap.challenge.client.github.dto.IssueDto;
import financial.swap.challenge.client.github.mapper.ContributorMapper;
import financial.swap.challenge.client.github.mapper.IssueMapper;
import financial.swap.challenge.web.StatsWeb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static financial.swap.challenge.client.github.GithubUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConcurrentStatsLoaderTest {
    @InjectMocks
    private ConcurrentStatsLoader loader;
    @Mock
    private IssueWebClient issueWebClient;
    @Mock
    private ContributorWebClient contributorWebClient;
    @Mock
    private IssueMapper issueMapper;
    @Mock
    private ContributorMapper contributorMapper;

    @Test
    void execute() {
        IssueDto issue = createIssueDto();
        ContributorDto contributor = createContributorDto();
        when(issueWebClient.findAll("schambeck", "api-github")).thenReturn(List.of(issue));
        when(contributorWebClient.findAll("schambeck", "api-github")).thenReturn(List.of(contributor));
        when(issueMapper.toWeb(issue)).thenReturn(createIssueWeb());
        when(contributorMapper.toWeb(contributor)).thenReturn(createContributorWeb());

        StatsWeb stats = loader.execute("schambeck", "api-github");

        assertThat(stats.getId()).isNull();
        assertThat(stats.getUser()).isEqualTo("schambeck");
        assertThat(stats.getRepository()).isEqualTo("api-github");
        assertThat(stats.getIssues()).hasSameElementsAs(List.of(createIssueWeb()));
        assertThat(stats.getContributors()).hasSameElementsAs(List.of(createContributorWeb()));
    }
}
