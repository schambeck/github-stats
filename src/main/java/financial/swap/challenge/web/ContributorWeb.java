package financial.swap.challenge.web;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContributorWeb implements Serializable {
    private Long id;
    private String name;
    private String user;
    private Long qtyCommits;
}
