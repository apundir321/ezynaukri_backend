package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.Organization;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;

@Repository
public interface JobRepoCustom {
	
	List<Job> findJobsByJobCriterias(Map<String,String> searchCriteria);
	
	Page<Job> findJobsByTags(List<String> tags,Map<String, Object> searchCriteria);
	
	public List<Organization> findOrgsByCriteria(Map<String, Object> searchCriteria);
	
	
	

}
