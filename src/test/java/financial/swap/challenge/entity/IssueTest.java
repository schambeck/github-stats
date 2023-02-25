package financial.swap.challenge.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static nl.jqno.equalsverifier.Warning.SURROGATE_KEY;

class IssueTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple()
                .forClass(Issue.class)
                .withPrefabValues(Stats.class, new Stats(1L), new Stats(2L))
                .suppress(SURROGATE_KEY)
                .verify();
    }
}
