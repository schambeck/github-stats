package financial.swap.challenge.client.github.mapper;

import financial.swap.challenge.entity.Issue;
import financial.swap.challenge.web.IssueWeb;
import org.junit.jupiter.api.Test;

import java.util.List;

import static financial.swap.challenge.util.GithubUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

class IssueMapperTest {
    private final IssueMapper mapper = new IssueMapper();

    @Test
    void toWebFromDto() {
        IssueWeb issue = mapper.toWeb(createIssueDto());
        assertThat(issue.getTitle()).isEqualTo("Issue 1");
        assertThat(issue.getAuthor()).isEqualTo("schambeck");
        assertThat(issue.getLabels()).hasSameElementsAs(List.of("bug"));
    }

    @Test
    void toWebFromEntity() {
        IssueWeb issue = mapper.toWeb(createIssue());
        assertThat(issue.getTitle()).isEqualTo("Issue 1");
        assertThat(issue.getAuthor()).isEqualTo("schambeck");
        assertThat(issue.getLabels()).hasSameElementsAs(List.of("bug"));
    }

    @Test
    void toEntity() {
        Issue issue = mapper.toEntity(createIssueWeb(), createStats());
        assertThat(issue.getTitle()).isEqualTo("Issue 1");
        assertThat(issue.getAuthor()).isEqualTo("schambeck");
        assertThat(issue.getLabels()).hasSameElementsAs(List.of("bug"));
    }
}
