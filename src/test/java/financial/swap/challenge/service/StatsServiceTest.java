package financial.swap.challenge.service;

import financial.swap.challenge.client.github.ConcurrentStatsLoader;
import financial.swap.challenge.mapper.StatsMapper;
import financial.swap.challenge.quartz.StatsScheduler;
import financial.swap.challenge.repository.StatsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static financial.swap.challenge.util.GithubUtil.createStats;
import static financial.swap.challenge.util.GithubUtil.createStatsWeb;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class StatsServiceTest {
    @InjectMocks
    private StatsService service;
    @Mock
    private ConcurrentStatsLoader loader;
    @Mock
    private StatsMapper mapper;
    @Mock
    private StatsRepository repo;
    @Mock
    private StatsScheduler scheduler;

    @Test
    void execute() {
        when(loader.execute("schambeck", "api-github")).thenReturn(createStatsWeb());
        when(mapper.toEntity(createStatsWeb())).thenReturn(createStats());
        when(repo.save(createStats())).thenReturn(createStats());
        when(mapper.toWeb(createStats())).thenReturn(createStatsWeb());
        doNothing().when(scheduler).execute(createStatsWeb());

        service.execute("schambeck", "api-github");

        verify(loader).execute("schambeck", "api-github");
        verify(mapper).toEntity(createStatsWeb());
        verify(repo).save(createStats());
        verify(mapper).toWeb(createStats());
        verify(scheduler).execute(createStatsWeb());
    }
}
