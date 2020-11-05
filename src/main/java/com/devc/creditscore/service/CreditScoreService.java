package com.devc.creditscore.service;

import java.util.Optional;

import com.devc.creditscore.entity.CreditScore;

public interface CreditScoreService {
	Optional<CreditScore> findByPhone(String phoneNumber);
}
