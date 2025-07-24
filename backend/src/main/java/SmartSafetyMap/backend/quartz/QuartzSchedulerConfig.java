package SmartSafetyMap.backend.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzSchedulerConfig {

    @Bean
    public JobDetail chatRoomCleanupJobDetail() {
        return JobBuilder.newJob(UTICDeleteInsertJob.class)
                .withIdentity("UTIC Info Delete Insert Job")
                .storeDurably()  // 작업이 트리거와 연결되지 않아도 저장되어야 함?
                .build();
    }

    @Bean
    public Trigger chatRoomCleanupTrigger(JobDetail jobDetail) {   // 작업이 언제 실행될 지 정의
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("ChatRoomCleanupTrigger")
                .startNow() // 애플리케이션 시작 시 즉시 작업 실행
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMinutes(5))
                .build();
    }


}