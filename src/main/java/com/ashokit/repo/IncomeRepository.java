package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.IncomeEntity;

public interface IncomeRepository extends JpaRepository<IncomeEntity,Integer> {

	public IncomeEntity findByCaseNumber(Integer caseNumber);
}
