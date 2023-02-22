package financial.swap.challenge.repository;

import financial.swap.challenge.entity.Stats;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatsRepository extends PagingAndSortingRepository<Stats, Long> {
}
