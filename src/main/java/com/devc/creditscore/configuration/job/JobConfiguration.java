package com.devc.creditscore.configuration.job;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.devc.creditscore.job.SyncDataCreditJob;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobLocator jobLocator;
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	@Bean
	public JobDetail syncDataCreditJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "importCreditScoreJob");
		jobDataMap.put("jobLauncher", jobLauncher);
		jobDataMap.put("jobLocator", jobLocator);

		return JobBuilder.newJob(SyncDataCreditJob.class).withIdentity(JobKey.jobKey("importCreditScoreJob"))
				.setJobData(jobDataMap).storeDurably().build();
	}

	@Bean
	@ConditionalOnProperty(value ="config.run-job",havingValue = "true")
	public Trigger syncDataCreditJobTrigger() {
		return TriggerBuilder.newTrigger().forJob(syncDataCreditJobDetail()).withIdentity("importCreditScoreJob")
				.build();
	}
}
