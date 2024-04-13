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
public class ChildDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childId;
	private String noOfKids;
	private String kidName;
	private String dob;
	private String kidSsn;
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	/*@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "caseNumber")
	private RegistrationEntity entity;*/
	private Integer caseNumber;


}
