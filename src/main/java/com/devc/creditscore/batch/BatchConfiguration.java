package com.devc.creditscore.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.devc.creditscore.entity.CreditScore;
import com.devc.creditscore.model.CreditScoreBase;
import com.devc.creditscore.model.CreditScoreRaw;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	 @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  @Bean
	  public FlatFileItemReader<CreditScoreRaw> reader() {
	    return new FlatFileItemReaderBuilder<CreditScoreRaw>()
	      .name("creditScoreItemReader")
	      .resource(new ClassPathResource("submission_ver5_BlackHole.csv"))
	      .delimited()
	      .names(new String[]{"msisdn", "percent"})
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<CreditScoreRaw>() {{
	        setTargetType(CreditScoreRaw.class);
	      }})
	      .build();
	  }

	  @Bean
	  public CreditScoreItemProcessor processor() {
	    return new CreditScoreItemProcessor();
	  }

	  @Bean
	  public JdbcBatchItemWriter<CreditScoreBase> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<CreditScoreBase>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT IGNORE INTO credit_score (id,msisdn, percent) VALUES (:id,:msisdn, :percent)")
	      .dataSource(dataSource)
	      .build();
	  }	
	  @Bean
	  public Job importCreditScoreJob(Step step1) {
	    return jobBuilderFactory.get("importCreditScoreJob")
	      .incrementer(new RunIdIncrementer())
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(DataSource dataSource) {
	    return stepBuilderFactory.get("step1")
	      .<CreditScoreRaw, CreditScoreBase> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer(dataSource))
	      .build();
	  }
}
