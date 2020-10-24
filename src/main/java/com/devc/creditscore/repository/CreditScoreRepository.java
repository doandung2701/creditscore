package com.devc.creditscore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devc.creditscore.entity.CreditScore;

public interface CreditScoreRepository extends JpaRepository<CreditScore, UUID>{

}
