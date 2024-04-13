package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.ChildDetailsEntity;
import com.ashokit.entity.EducationEntity;

public interface ChildDetailsRepository extends JpaRepository<ChildDetailsEntity,Integer> {

	public ChildDetailsEntity findByCaseNumber(Integer caseNumber);
}
