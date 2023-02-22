package financial.swap.challenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "CONTRIBUTOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contributor {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CONTRIBUTOR")
    @SequenceGenerator(name = "SEQ_CONTRIBUTOR", sequenceName = "SEQ_CONTRIBUTOR", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Stats stats;
    private String name;
    private String user;
    private Long qtyCommits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contributor that = (Contributor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
