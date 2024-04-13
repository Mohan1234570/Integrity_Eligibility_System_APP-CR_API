package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.EligibilityForm;
import com.ashokit.service.EligibilityService;


@RestController
public class EligibilityRestController {
	
	@Autowired
	private EligibilityService service;
	
	@PostMapping(value = "/eligibility")
	public ResponseEntity<String> createEducation(@RequestBody EligibilityForm form){

		String status = service.checkEligibility(form);

		return new ResponseEntity<>(status,HttpStatus.CREATED); 

	}


}
