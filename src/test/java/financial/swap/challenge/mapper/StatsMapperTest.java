package financial.swap.challenge.mapper;

import financial.swap.challenge.client.github.mapper.ContributorMapper;
import financial.swap.challenge.client.github.mapper.IssueMapper;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.web.StatsWeb;
import org.junit.jupiter.api.Test;

import java.util.List;

import static financial.swap.challenge.util.GithubUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

class StatsMapperTest {
    private final StatsMapper mapper = new StatsMapper(new IssueMapper(), new ContributorMapper());

    @Test
    void toEntity() {
        Stats stats = mapper.toEntity(createStatsWeb());
        assertThat(stats.getId()).isEqualTo(1L);
        assertThat(stats.getUser()).isEqualTo("schambeck");
        assertThat(stats.getRepository()).isEqualTo("api-github");
        assertThat(stats.getIssues()).hasSameElementsAs(List.of(createIssue()));
        assertThat(stats.getContributors()).hasSameElementsAs(List.of(createContributor()));
    }

    @Test
    void toWeb() {
        StatsWeb stats = mapper.toWeb(createStats());
        assertThat(stats.getId()).isEqualTo(1L);
        assertThat(stats.getUser()).isEqualTo("schambeck");
        assertThat(stats.getRepository()).isEqualTo("api-github");
        assertThat(stats.getIssues()).hasSameElementsAs(List.of(createIssueWeb()));
        assertThat(stats.getContributors()).hasSameElementsAs(List.of(createContributorWeb()));
    }
}
