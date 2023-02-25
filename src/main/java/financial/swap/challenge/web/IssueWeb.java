package financial.swap.challenge.web;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class IssueWeb implements Serializable {
    private Long id;
    private String title;
    private String author;
    private List<String> labels;
}
