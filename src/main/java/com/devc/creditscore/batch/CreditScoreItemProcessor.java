package com.devc.creditscore.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.devc.creditscore.entity.CreditScore;

public class CreditScoreItemProcessor implements ItemProcessor<CreditScore, CreditScore> {

	  private static final Logger log = LoggerFactory.getLogger(CreditScoreItemProcessor.class);

	  @Override
	  public CreditScore process(final CreditScore creditScore) throws Exception {
	    //convert percent to score
	    return creditScore;
	  }

	}