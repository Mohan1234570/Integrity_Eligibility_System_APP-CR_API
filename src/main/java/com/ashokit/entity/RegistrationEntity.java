package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class RegistrationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer registerId;
	private Integer caseNumber;
	private String city;
	private String dob;
	private String fullname;
	private String gender;
	private String email;
	private String houseNum;
	private Long ssn;
	private String state;
	private String mobile;
	@CreationTimestamp
	private LocalDate created;
}