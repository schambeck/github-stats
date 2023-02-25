package financial.swap.challenge.client.github.mapper;

import financial.swap.challenge.entity.Contributor;
import financial.swap.challenge.web.ContributorWeb;
import org.junit.jupiter.api.Test;

import static financial.swap.challenge.client.github.GithubUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ContributorMapperTest {
    private final ContributorMapper mapper = new ContributorMapper();

    @Test
    void toWebFromDto() {
        ContributorWeb contributor = mapper.toWeb(createContributorDto());
        assertThat(contributor.getName()).isEqualTo("schambeck");
        assertThat(contributor.getUser()).isEqualTo("schambeck");
        assertThat(contributor.getQtyCommits()).isEqualTo(2L);
    }

    @Test
    void toWebFromEntity() {
        ContributorWeb contributor = mapper.toWeb(createContributor());
        assertThat(contributor.getName()).isEqualTo("Marcelo");
        assertThat(contributor.getUser()).isEqualTo("schambeck");
        assertThat(contributor.getQtyCommits()).isEqualTo(2L);
    }

    @Test
    void toEntity() {
        Contributor contributor = mapper.toEntity(createContributorWeb(), createStats());
        assertThat(contributor.getName()).isEqualTo("Marcelo");
        assertThat(contributor.getUser()).isEqualTo("schambeck");
        assertThat(contributor.getQtyCommits()).isEqualTo(2L);
    }
}
