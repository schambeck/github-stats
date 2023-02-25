package financial.swap.challenge.quartz;

import financial.swap.challenge.web.StatsWeb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static financial.swap.challenge.util.GithubUtil.createStatsWeb;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatsScheduler.class)
@TestPropertySource(locations = "/application.properties")
class StatsSchedulerTest {
    private static final Integer DAYS_AFTER = 1;
    @Autowired
    private StatsScheduler statsScheduler;
    @MockBean
    private Scheduler scheduler;

    @Test
    void execute() throws SchedulerException {
        JobDetail job = createJobDetail(createStatsWeb());
        Trigger trigger = createTrigger();
        when(scheduler.scheduleJob(job, trigger)).thenReturn(createStartAt());

        statsScheduler.execute(createStatsWeb());
    }

    private static JobDetail createJobDetail(StatsWeb stats) {
        JobDataMap params = new JobDataMap();
        params.put("statsId", stats.getId());
        return JobBuilder.newJob()
                .ofType(StatsJob.class)
                .usingJobData(params)
                .build();
    }

    private Trigger createTrigger() {
        return TriggerBuilder.newTrigger()
                .startAt(createStartAt())
                .build();
    }

    private Date createStartAt() {
        return Date.from(LocalDateTime.now()
                .plusDays(DAYS_AFTER)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
