package financial.swap.challenge.quartz;

import financial.swap.challenge.client.webhook.StatsWebhookWebClient;
import financial.swap.challenge.entity.Stats;
import financial.swap.challenge.mapper.StatsMapper;
import financial.swap.challenge.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatsJob implements Job {
    private final StatsWebhookWebClient webhookClient;
    private final StatsRepository repository;
    private final StatsMapper mapper;

    @Override
    public void execute(JobExecutionContext context) {
        Long statsId = context.getJobDetail().getJobDataMap().getLong("statsId");
        log.debug("Executing  job {}", statsId);
        Stats stats = repository.findById(statsId).orElseThrow();
        webhookClient.post(mapper.toWeb(stats));
        log.debug("Finishing  job {}", statsId);
    }
}
