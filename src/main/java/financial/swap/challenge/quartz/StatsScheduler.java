package financial.swap.challenge.quartz;

import financial.swap.challenge.web.StatsWeb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsScheduler {
    @Value("${custom.trigger.days-after}")
    private Integer daysAfter;
    private final Scheduler scheduler;

    public void execute(StatsWeb stats) {
        try {
            log.debug("Scheduling job {}", stats.getId());
            JobDetail job = createJobDetail(stats);
            Trigger trigger = createTrigger();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
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
                .plusDays(daysAfter)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
