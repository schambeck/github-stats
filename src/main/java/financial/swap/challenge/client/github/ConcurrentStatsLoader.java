package financial.swap.challenge.client.github;

import financial.swap.challenge.web.StatsWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ConcurrentStatsLoader {
    private final ConcurrentStatsAllLoader allLoader;

    public StatsWeb execute(String username, String repository) {
        try {
            return allLoader.execute(username, repository);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
