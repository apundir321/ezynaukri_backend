package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.JobCategory;

@Repository
public interface JobCategoryRepo extends JpaRepository<JobCategory,Integer>{

	public JobCategory findByJobCategoryName(String jobCategoryName);
	
}

