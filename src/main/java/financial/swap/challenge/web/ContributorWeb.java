package financial.swap.challenge.web;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContributorWeb implements Serializable {
    private String name;
    private String user;
    private Long qtyCommits;
}
