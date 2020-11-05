package com.devc.creditscore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devc.creditscore.entity.CreditScore;
import com.devc.creditscore.repository.CreditScoreRepository;

@Service
@Transactional
public class CreditScoreServiceImpl implements CreditScoreService{
	@Autowired
	private CreditScoreRepository creditScoreRepository;

	@Override
	public Optional<CreditScore> findByPhone(String phoneNumber) {
		// TODO Auto-generated method stub
		Optional<CreditScore> creOptional=this.creditScoreRepository.findByMsisdn(phoneNumber);
		return creOptional;
	}

}
