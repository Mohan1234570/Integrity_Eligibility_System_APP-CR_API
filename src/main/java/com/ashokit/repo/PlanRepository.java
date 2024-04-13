package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.PlanDetails;

public interface PlanRepository extends JpaRepository<PlanDetails, Integer> {

	
	public PlanDetails findByCaseNumber(Integer caseNumber);
}
