package financial.swap.challenge.mapper;

import financial.swap.challenge.client.github.mapper.ContributorMapper;
import financial.swap.challenge.client.github.mapper.IssueMapper;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.web.StatsWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StatsMapper {
    private final IssueMapper issueMapper;
    private final ContributorMapper contributorMapper;

    public Stats toEntity(StatsWeb web) {
        Stats stats = Stats.builder()
                .id(web.getId())
                .user(web.getUser())
                .repository(web.getRepository())
                .build();
        stats.setIssues(web.getIssues().stream()
                .map(issue -> issueMapper.toEntity(issue, stats))
                .collect(toList()));
        stats.setContributors(web.getContributors().stream()
                .map(contributor -> contributorMapper.toEntity(contributor, stats))
                .collect(toList()));
        return stats;
    }

    public StatsWeb toWeb(Stats stats) {
        return StatsWeb.builder()
                .id(stats.getId())
                .user(stats.getUser())
                .repository(stats.getRepository())
                .issues(stats.getIssues().stream()
                        .map(issueMapper::toWeb)
                        .collect(toList()))
                .contributors(stats.getContributors().stream()
                        .map(contributorMapper::toWeb)
                        .collect(toList()))
                .build();
    }

}
