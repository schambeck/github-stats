package financial.swap.challenge.service;

import financial.swap.challenge.client.github.ConcurrentStatsLoader;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.mapper.StatsMapper;
import financial.swap.challenge.quartz.StatsScheduler;
import financial.swap.challenge.repository.StatsRepository;
import financial.swap.challenge.web.StatsWeb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {
    private final ConcurrentStatsLoader loader;
    private final StatsMapper mapper;
    private final StatsRepository repo;
    private final StatsScheduler scheduler;

    public void execute(String username, String repository) {
        StatsWeb web = loader.execute(username, repository);
        Stats stats = mapper.toEntity(web);
        Stats created = repo.save(stats);
        scheduler.execute(mapper.toWeb(created));
    }
}
