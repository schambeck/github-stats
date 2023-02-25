package financial.swap.challenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "STATS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_STATS")
    @SequenceGenerator(name = "SEQ_STATS", sequenceName = "SEQ_STATS", allocationSize = 1)
    private Long id;
    private String user;
    private String repository;
    @OneToMany(mappedBy = "stats", cascade = ALL, orphanRemoval = true)
    private List<Issue> issues;
    @OneToMany(mappedBy = "stats", cascade = ALL, orphanRemoval = true)
    private List<Contributor> contributors;

    public Stats(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return Objects.equals(id, stats.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
