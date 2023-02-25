package financial.swap.challenge.quartz;

import financial.swap.challenge.client.webhook.StatsWebhookWebClient;
import financial.swap.challenge.mapper.StatsMapper;
import financial.swap.challenge.repository.StatsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.impl.JobDetailImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static financial.swap.challenge.util.GithubUtil.createStats;
import static financial.swap.challenge.util.GithubUtil.createStatsWeb;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StatsJobTest {
    @InjectMocks
    private StatsJob job;
    @Mock
    private StatsWebhookWebClient webhookClient;
    @Mock
    private StatsRepository repository;
    @Mock
    private StatsMapper mapper;
    @Mock
    private JobExecutionContext context;

    @Test
    void execute() {
        when(repository.findById(1L)).thenReturn(Optional.of(createStats()));
        when(mapper.toWeb(createStats())).thenReturn(createStatsWeb());
        doNothing().when(webhookClient).post(createStatsWeb());

        JobDetail detail = new JobDetailImpl();
        detail.getJobDataMap().put("statsId", 1L);
        when(context.getJobDetail()).thenReturn(detail);
        job.execute(context);
    }
}
