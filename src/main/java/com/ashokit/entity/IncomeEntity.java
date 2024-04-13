package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class IncomeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	
	
	private Double monthlySalary;
	
	private Double rentIncome;
	
	private Double propertyIncome;
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	/*@OneToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "caseNumber")
	private PlanDetails plan;*/
	private Integer caseNumber;

}
