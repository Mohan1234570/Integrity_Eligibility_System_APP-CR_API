package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.EducationEntity;

public interface EducationRepository extends JpaRepository<EducationEntity, Integer> {


	public EducationEntity findByCaseNumber(Integer caseNumber);

}