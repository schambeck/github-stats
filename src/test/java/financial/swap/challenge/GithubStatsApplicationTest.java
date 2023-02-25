package financial.swap.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GithubStatsApplicationTest {
    @Autowired
    private GithubStatsApplication context;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }
}
