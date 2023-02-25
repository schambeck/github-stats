package financial.swap.challenge.client.github;

import financial.swap.challenge.client.github.dto.ContributorDto;
import financial.swap.challenge.client.github.dto.IssueDto;
import financial.swap.challenge.client.github.dto.LabelDto;
import financial.swap.challenge.client.github.dto.UserDto;
import financial.swap.challenge.web.ContributorWeb;
import financial.swap.challenge.web.IssueWeb;

import java.util.List;

final class GithubUtil {
    private GithubUtil() {
    }

    static ContributorWeb createContributorWeb() {
        return ContributorWeb.builder()
                .name("Marcelo")
                .user("schambeck")
                .qtyCommits(2L)
                .build();
    }

    static IssueWeb createIssueWeb() {
        return IssueWeb.builder()
                .title("Issue 1")
                .author("schambeck")
                .labels(List.of("bug"))
                .build();
    }

    static ContributorDto createContributorDto() {
        return ContributorDto.builder()
                .login("schambeck")
                .contributions(2L)
                .build();
    }

    static IssueDto createIssueDto() {
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
