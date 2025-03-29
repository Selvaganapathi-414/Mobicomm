package com.techm.mobileprepaidrechargesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.mobileprepaidrechargesystem.model.PlanCategory;
import com.techm.mobileprepaidrechargesystem.repository.PlanCategoryRepository;

@Service
public class PlanCategoryService {
	
	private final PlanCategoryRepository planCategoryRepository;
	
	public PlanCategoryService(PlanCategoryRepository planCategoryRepository){
		this.planCategoryRepository = planCategoryRepository;
	}

	public List<PlanCategory> getAllCategories() {
		return planCategoryRepository.findAll();
	}

	public PlanCategory addNewCategory(PlanCategory category) {
		return planCategoryRepository.save(category);
	}

	public void deleteAllById(List<Long> categoryIds) {
		planCategoryRepository.deleteAllById(categoryIds);
		
	}

}
