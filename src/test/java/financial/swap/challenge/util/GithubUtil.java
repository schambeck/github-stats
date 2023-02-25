package financial.swap.challenge.util;

import financial.swap.challenge.client.github.dto.ContributorDto;
import financial.swap.challenge.client.github.dto.IssueDto;
import financial.swap.challenge.client.github.dto.LabelDto;
import financial.swap.challenge.client.github.dto.UserDto;
import financial.swap.challenge.entity.Contributor;
import financial.swap.challenge.entity.Issue;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.web.ContributorWeb;
import financial.swap.challenge.web.IssueWeb;
import financial.swap.challenge.web.StatsWeb;

import java.util.List;

public final class GithubUtil {
    private GithubUtil() {
    }

    public static ContributorWeb createContributorWeb() {
        return ContributorWeb.builder()
                .id(1L)
                .name("Marcelo")
                .user("schambeck")
                .qtyCommits(2L)
                .build();
    }

    public static IssueWeb createIssueWeb() {
        return IssueWeb.builder()
                .id(1L)
                .title("Issue 1")
                .author("schambeck")
                .labels(List.of("bug"))
                .build();
    }

    public static Stats createStats() {
        return Stats.builder()
                .id(1L)
                .user("schambeck")
                .repository("api-github")
                .issues(List.of(createIssue()))
                .contributors(List.of(createContributor()))
                .build();
    }

    public static StatsWeb createStatsWeb() {
        return StatsWeb.builder()
                .id(1L)
                .user("schambeck")
                .repository("api-github")
                .issues(List.of(createIssueWeb()))
                .contributors(List.of(createContributorWeb()))
                .build();
    }

    public static Contributor createContributor() {
        return Contributor.builder()
                .id(1L)
                .name("Marcelo")
                .user("schambeck")
                .qtyCommits(2L)
                .build();
    }

    public static Issue createIssue() {
        return Issue.builder()
                .id(1L)
                .title("Issue 1")
                .author("schambeck")
                .labels(List.of("bug"))
                .build();
    }

    public static ContributorDto createContributorDto() {
        return ContributorDto.builder()
                .login("schambeck")
                .contributions(2L)
                .build();
    }

    public static IssueDto createIssueDto() {
        return IssueDto.builder()
                .title("Issue 1")
                .user(UserDto.builder()
                        .login("schambeck")
                        .build())
                .labels(List.of(LabelDto.builder()
                        .name("bug")
                        .build()))
                .build();
    }
}
