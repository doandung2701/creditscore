package com.devc.creditscore.dto;

import com.devc.creditscore.entity.CreditScore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreDTO {
	private String msisdn;
	private double value;

	public static CreditScoreDTO build(int fromValue, int toValue, CreditScore c) {
		// TODO Auto-generated method stub
		CreditScoreDTO creditScoreDTO=new CreditScoreDTO();
		creditScoreDTO.setMsisdn(c.getMsisdn());
		creditScoreDTO.setValue(fromValue+Math.ceil((toValue-fromValue)*c.getPercent()));
		return creditScoreDTO;
	}
}
