package com.devc.creditscore.configuration.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devc.creditscore.job.SyncDataCreditJob;

@Configuration
public class JobConfiguration {
	@Bean
    public JobDetail syncDataCreditJobDetail() {
        return JobBuilder
                .newJob(SyncDataCreditJob.class)
                .withIdentity(JobKey.jobKey("syncDataCredit"))
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger syncDataCreditJobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(syncDataCreditJobDetail())
                .withIdentity(TriggerKey.triggerKey("syncDataCredit"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 23 * * * ?"))
                .build();
    }
}
