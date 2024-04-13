package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {

	
	public RegistrationEntity findByCaseNumber(Integer caseNumber);
}
