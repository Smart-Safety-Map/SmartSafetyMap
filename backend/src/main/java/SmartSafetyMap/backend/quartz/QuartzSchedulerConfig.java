package SmartSafetyMap.backend.quartz;

import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class QuartzSchedulerConfig {

//    @Bean
//    public JobDetail UticQuartzJobDetail() {
//        return JobBuilder.newJob(UTICDeleteInsertJob.class)
//                .withIdentity("UTIC Info Delete Insert Job")
//                .storeDurably()
//                .build();
//    }
//
//
//
//    @Bean
//    public Trigger chatRoomCleanupTrigger(JobDetail UticQuartzJobDetail) {   // 작업이 언제 실행될 지 정의
//        return TriggerBuilder.newTrigger()
//                .forJob(UticQuartzJobDetail)
//                .withIdentity("ChatRoomCleanupTrigger")
//                .startNow() // 애플리케이션 시작 시 즉시 작업 실행
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInMinutes(5))
//                .build();
//    }

    @Bean
    public JobDetail NticQuartzJobDetail() {
        return JobBuilder.newJob(NticQuartzJob.class)
                .withIdentity("Ntic Info Delete Insert Job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger NticQuartzTrigger(JobDetail NticQuartzJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(NticQuartzJobDetail)
                .withIdentity("Ntic Info Delete Insert Job")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever())
                .build();
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SpringBeanJobFactory springBeanJobFactory , Trigger NticQuartzTrigger,
                                                     JobDetail NticQuartzJobDetail, PlatformTransactionManager   transactionManager) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory);
        factory.setAutoStartup(true);
        factory.setJobDetails(NticQuartzJobDetail);
        factory.setTriggers(NticQuartzTrigger);
        factory.setTransactionManager(transactionManager);
        return factory;
    }





}