package com.devc.creditscore.batch;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.devc.creditscore.model.CreditScoreBase;
import com.devc.creditscore.model.CreditScoreRaw;

public class CreditScoreItemProcessor implements ItemProcessor<CreditScoreRaw, CreditScoreBase> {

	  private static final Logger log = LoggerFactory.getLogger(CreditScoreItemProcessor.class);

	  @Override
	  public CreditScoreBase process(final CreditScoreRaw creditScore) throws Exception {
	    //convert percent to score
		 
		  CreditScoreBase cScore=new CreditScoreBase();
		  cScore.setId(UUID.randomUUID().toString());
		  cScore.setMsisdn(creditScore.getMsisdn());
		  cScore.setPercent(Double.parseDouble(creditScore.getPercent()));
	    return cScore;
	  }

	}