package financial.swap.challenge.quartz;

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

    public void execute(Long statsId) {
        try {
            log.debug("Scheduling job {}", statsId);
            JobDetail job = createJobDetail(statsId);
            Trigger trigger = createTrigger();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private static JobDetail createJobDetail(Long statsId) {
        return JobBuilder.newJob()
                .ofType(StatsJob.class)
                .usingJobData(createParams(statsId))
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

    private static JobDataMap createParams(Long statsId) {
        JobDataMap params = new JobDataMap();
        params.put("statsId", statsId);
        return params;
    }
}
