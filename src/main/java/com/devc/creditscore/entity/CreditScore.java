package com.devc.creditscore.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditScore {
	private UUID id;
	private String phone;
	private double score;
	
}
