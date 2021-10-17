package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.JobTags;

@Repository
public interface JobTagsRepo extends JpaRepository<JobTags, Long>{
	
	public JobTags findByName(String name);
}
