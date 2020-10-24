package com.devc.creditscore.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	 @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  @Bean
	  public FlatFileItemReader<CreditScore> reader() {
	    return new FlatFileItemReaderBuilder<CreditScore>()
	      .name("creditScoreItemReader")
	      .resource(new ClassPathResource("sample-data.csv"))
	      .delimited()
	      .names(new String[]{"phone", "score"})
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<CreditScore>() {{
	        setTargetType(CreditScore.class);
	      }})
	      .build();
	  }

	  @Bean
	  public CreditScoreItemProcessor processor() {
	    return new CreditScoreItemProcessor();
	  }

	  @Bean
	  public JdbcBatchItemWriter<CreditScore> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<CreditScore>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO creditscore (phone, score) VALUES (:phone, :score)")
	      .dataSource(dataSource)
	      .build();
	  }	
}
