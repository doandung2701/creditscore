package com.devc.creditscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

@SpringBootApplication(exclude={BatchAutoConfiguration.class})
public class CreditscoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditscoreApplication.class, args);
	}

}
