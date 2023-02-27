package financial.swap.challenge.client.github;

import financial.swap.challenge.web.StatsWeb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static financial.swap.challenge.util.GithubUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConcurrentStatsLoaderTest {
    @InjectMocks
    private ConcurrentStatsLoader loader;
    @Mock
    private ConcurrentStatsAllLoader allLoader;

    @Test
    void execute() {
        when(loader.execute("schambeck", "api-github")).thenReturn(createStatsWeb());

        StatsWeb stats = loader.execute("schambeck", "api-github");

        assertThat(stats.getId()).isEqualTo(1L);
        assertThat(stats.getUser()).isEqualTo("schambeck");
        assertThat(stats.getRepository()).isEqualTo("api-github");
        assertThat(stats.getIssues()).hasSameElementsAs(List.of(createIssueWeb()));
        assertThat(stats.getContributors()).hasSameElementsAs(List.of(createContributorWeb()));
    }

    @Test
    void executeExecutionException() throws ExecutionException, InterruptedException {
        when(allLoader.execute(null, null)).thenThrow(ExecutionException.class);

        assertThrows(RuntimeException.class, () -> loader.execute(null, null));
    }

    @Test
    void executeInterruptedException() throws ExecutionException, InterruptedException {
        when(allLoader.execute(null, null)).thenThrow(InterruptedException.class);

        assertThrows(RuntimeException.class, () -> loader.execute(null, null));
    }
}
