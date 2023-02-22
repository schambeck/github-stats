package financial.swap.challenge.web;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsWeb implements Serializable {
    private Long id;
    private String user;
    private String repository;
    private List<IssueWeb> issues;
    private List<ContributorWeb> contributors;
}
