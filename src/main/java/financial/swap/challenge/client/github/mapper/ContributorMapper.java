package financial.swap.challenge.client.github.mapper;

import financial.swap.challenge.client.github.dto.ContributorDto;
import financial.swap.challenge.entity.Contributor;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.web.ContributorWeb;
import org.springframework.stereotype.Service;

@Service
public class ContributorMapper {
    public ContributorWeb toWeb(ContributorDto contributor) {
        return ContributorWeb.builder()
                .name(contributor.getLogin())
                .user(contributor.getLogin())
                .qtyCommits(contributor.getContributions())
                .build();
    }

    public ContributorWeb toWeb(Contributor contributor) {
        return ContributorWeb.builder()
                .id(contributor.getId())
                .name(contributor.getName())
                .user(contributor.getUser())
                .qtyCommits(contributor.getQtyCommits())
                .build();
    }

    public Contributor toEntity(ContributorWeb contributor, Stats stats) {
        return Contributor.builder()
                .id(contributor.getId())
                .stats(stats)
                .name(contributor.getName())
                .user(contributor.getUser())
                .qtyCommits(contributor.getQtyCommits())
                .build();
    }
}
