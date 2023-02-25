package financial.swap.challenge.client.github.mapper;

import financial.swap.challenge.client.github.dto.IssueDto;
import financial.swap.challenge.client.github.dto.LabelDto;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.entity.Issue;
import financial.swap.challenge.web.IssueWeb;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class IssueMapper {
    public IssueWeb toWeb(IssueDto issue) {
        return IssueWeb.builder()
                .title(issue.getTitle())
                .author(issue.getUser().getLogin())
                .labels(issue.getLabels().stream()
                        .map(LabelDto::getName)
                        .collect(toList()))
                .build();
    }

    public IssueWeb toWeb(Issue issue) {
        return IssueWeb.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .author(issue.getAuthor())
                .labels(issue.getLabels())
                .build();
    }

    public Issue toEntity(IssueWeb issue, Stats stats) {
        return Issue.builder()
                .id(issue.getId())
                .stats(stats)
                .title(issue.getTitle())
                .author(issue.getAuthor())
                .labels(issue.getLabels())
                .build();
    }
}
