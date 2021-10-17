package com.easynaukri.java.easynaukriApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easynaukri.java.easynaukriApplication.dao.JobCategoryRepo;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;

@Service
public class JobCategoryService {

	@Autowired
	private JobCategoryRepo jobCategoryRepo;
	
	
	public JobCategory addCategory(JobCategory category)throws Exception
	{
		JobCategory jCategory = null;
		try {
			JobCategory jobCategory = jobCategoryRepo.findByJobCategoryName(category.getJobCategoryName());
			if(jobCategory!=null)
			{
				throw new Exception("JobCategory already Exists");
			}
			jCategory = jobCategoryRepo.save(category);
			
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		return jCategory;
	}
	
	public List<JobCategory> getCategories()throws Exception
	{
		List<JobCategory> categories = null;
		try {
			return jobCategoryRepo.findAll();
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}		
	}
	
	public Optional<JobCategory> getCategoryById(int categoryId)
	{
		return jobCategoryRepo.findById(categoryId);
	}
}
