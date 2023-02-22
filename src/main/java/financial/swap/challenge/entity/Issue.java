package financial.swap.challenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "ISSUE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_ISSUE")
    @SequenceGenerator(name = "SEQ_ISSUE", sequenceName = "SEQ_ISSUE", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Stats stats;
    private String title;
    private String author;
    @ElementCollection
    private List<String> labels;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
