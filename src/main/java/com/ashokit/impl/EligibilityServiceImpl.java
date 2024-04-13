package com.ashokit.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.EligibilityForm;
import com.ashokit.entity.ChildDetailsEntity;
import com.ashokit.entity.EducationEntity;
import com.ashokit.entity.EligibilityEntity;
import com.ashokit.entity.IncomeEntity;
import com.ashokit.entity.PlanDetails;
import com.ashokit.entity.RegistrationEntity;
import com.ashokit.repo.ChildDetailsRepository;
import com.ashokit.repo.EducationRepository;
import com.ashokit.repo.EligibilityRepository;
import com.ashokit.repo.IncomeRepository;
import com.ashokit.repo.PlanRepository;
import com.ashokit.repo.RegistrationRepository;
import com.ashokit.service.EligibilityService;
import com.ashokit.utils.AgeCalculation;

@Service
public class EligibilityServiceImpl implements EligibilityService {
	@Autowired
	private EligibilityRepository repo;
	@Autowired
	private RegistrationRepository registerrepo;
	@Autowired
	private EducationRepository educationrepo;
	@Autowired
	private ChildDetailsRepository childrepo;

	@Autowired
	private IncomeRepository incomerepo;

	@Autowired
	private PlanRepository planrepo;

	@Override
	public String checkEligibility(EligibilityForm form) {
		boolean isCaseNumberPresent = false;
		String planName = null;
		EligibilityEntity entity = new EligibilityEntity();
		List<EligibilityEntity> findAll = repo.findAll();
		for (EligibilityEntity caseNmber : findAll) {
			Integer caseNumber = caseNmber.getCaseNumber();
			Integer caseNumber2 = form.getCaseNumber();
			System.out.println("CaseNumber: " + caseNmber.getCaseNumber());
			if (caseNumber.equals(caseNumber2)) {
				planName = caseNmber.getPlanName();
				isCaseNumberPresent = true;
				break;
			}
		}
		if (isCaseNumberPresent) {
			return "Already Existed with" + planName;
		}
		PlanDetails planNames = planrepo.findByCaseNumber(form.getCaseNumber());
		IncomeEntity income = incomerepo.findByCaseNumber(form.getCaseNumber());
		EducationEntity education = educationrepo.findByCaseNumber(form.getCaseNumber());
		List<ChildDetailsEntity> childDetailsEntity = childrepo.findAll();
		String planName2 = planNames.getPlanName();

		List<Integer> childAgeList = new ArrayList<>();
		int childAge = 0;
		for (ChildDetailsEntity ChildrenDtlsEntity : childDetailsEntity) {
			Integer caseNumber = ChildrenDtlsEntity.getCaseNumber();
			Integer caseNumber2 = form.getCaseNumber();
			System.out.println("CaseNumber: " + ChildrenDtlsEntity.getCaseNumber());
			if (caseNumber.equals(caseNumber2)) {
				String dob = ChildrenDtlsEntity.getDob();
				LocalDate parse = LocalDate.parse(dob);
				LocalDate currentDate = LocalDate.now();
				Period age = Period.between(parse, currentDate);
				childAge = age.getYears();
				boolean addChildDob = childAgeList.add(childAge);
			}
		
			Double salary = income.getMonthlySalary();
			Double propertyIncome = income.getPropertyIncome();
			Double rentIncome = income.getRentIncome();
			Double total = salary + propertyIncome + rentIncome;
		
		

		if (planName2.equalsIgnoreCase("CCAP")) {

			int count = 0;
			for (int i = 0; i < childAgeList.size(); i++) {
				Integer childAges = childAgeList.get(i);
				if (childAges <= 16) {
					count++;
				}
			}
			
			if (total < 50000 && count >= 1) {
				entity.setBenefitAmount(5000.00);
				entity.setStartDate(LocalDate.of(2023, 6, 28));
				entity.setEndDate(LocalDate.now().plusYears(5));
				entity.setPlanStatus("approved");
				entity.setPlanName(planNames.getPlanName());
				entity.setDenialReason("NA");
			} else {
				entity.setBenefitAmount(00.00);
				entity.setPlanStatus("denied");
				entity.setStartDate(null);
				entity.setEndDate(null);
				entity.setPlanName(planNames.getPlanName());
				entity.setDenialReason("Credentials are not valid");

			}
			}
			}

		
		
		if (planName2.equalsIgnoreCase("SNAP")) {
			if (income != null) {
				Double salary = income.getMonthlySalary();
				Double propertyIncome = income.getPropertyIncome();
				Double rentIncome = income.getRentIncome();
				Double total = salary + propertyIncome + rentIncome;
				if (total < 100000) {
					entity.setBenefitAmount(5000.00);
					entity.setStartDate(LocalDate.of(2023, 6, 28));
					entity.setEndDate(LocalDate.now().plusYears(5));
					entity.setPlanStatus("approved");
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("NA");
				} else {
					entity.setBenefitAmount(00.00);
					entity.setPlanStatus("denied");
					entity.setStartDate(null);
					entity.setEndDate(null);
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("Credentials are not valid");
				}

			}
		}

		if (planName2.equalsIgnoreCase("Medicaid")) {
			if (income != null) {
				Double salary = income.getMonthlySalary();
				Double propertyIncome = income.getPropertyIncome();
				Double rentIncome = income.getRentIncome();
				Double total = salary + propertyIncome + rentIncome;
				if (total < 32000) {
					entity.setBenefitAmount(5000.00);
					entity.setStartDate(LocalDate.of(2023, 6, 28));
					entity.setEndDate(LocalDate.now().plusYears(5));
					entity.setPlanStatus("approved");
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("NA");
				} else {
					entity.setBenefitAmount(00.00);
					entity.setPlanStatus("denied");
					entity.setStartDate(null);
					entity.setEndDate(null);
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("Credentials are not valid");
				}

			}
		}

		if (planName2.equalsIgnoreCase("Medicare")) {
			RegistrationEntity findByCaseNumber = registerrepo.findByCaseNumber(form.getCaseNumber());
			String dob = findByCaseNumber.getDob();
			LocalDate parse = LocalDate.parse(dob);
			LocalDate currentDate = LocalDate.now();
			Period age = Period.between(parse, currentDate);
			String formatAge = AgeCalculation.formatAge(age);
			int comparisonValue = formatAge.compareTo("65");

			if (comparisonValue <= 0) {
				entity.setBenefitAmount(00.00);
				entity.setPlanStatus("denied");
				entity.setStartDate(null);
				entity.setEndDate(null);
				entity.setPlanName(planNames.getPlanName());
				entity.setDenialReason("Credentials are not valid");

			} else {
				entity.setBenefitAmount(10000.00);
				entity.setStartDate(LocalDate.of(2023, 6, 28));
				entity.setEndDate(LocalDate.now().plusYears(5));
				entity.setPlanStatus("approved");
				entity.setPlanName(planNames.getPlanName());
				entity.setDenialReason("NA");

			}

		}
		if (planName2.equalsIgnoreCase("Riw")) {
			Double salary = income.getMonthlySalary();
			if (salary == 0 || salary == null) {
				String highestdegree = education.getHighestdegree();
				if (highestdegree.equalsIgnoreCase("degree") || highestdegree.equalsIgnoreCase("btech")
						|| highestdegree.equalsIgnoreCase("pg")) {
					entity.setBenefitAmount(10000.00);
					entity.setStartDate(LocalDate.of(2023, 6, 28));
					entity.setEndDate(LocalDate.now().plusYears(5));
					entity.setPlanStatus("approved");
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("NA");
				} else {
					entity.setBenefitAmount(00.00);
					entity.setPlanStatus("denied");
					entity.setStartDate(null);
					entity.setEndDate(null);
					entity.setPlanName(planNames.getPlanName());
					entity.setDenialReason("Credentials are not valid");
				}

			}
		}

		/*
		 * if(planName2.equalsIgnoreCase("CCAP")) {
		 * 
		 * 
		 * childDetailsEntity.get } if(income!=null) { Double salary =
		 * income.getMonthlySalary(); Double propertyIncome =
		 * income.getPropertyIncome(); Double rentIncome = income.getRentIncome();
		 * Double total = salary+ propertyIncome+rentIncome; if(total<=5000) {
		 * 
		 * }
		 */

		BeanUtils.copyProperties(form, entity);
		repo.save(entity);
		return "success";
	}

}
