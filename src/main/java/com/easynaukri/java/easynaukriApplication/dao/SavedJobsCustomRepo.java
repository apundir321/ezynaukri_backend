package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;

@Repository
public interface SavedJobsCustomRepo {
	
	SavedJobs findSavedJobByUserIdAndJobId(String userId,String jobId);
	
	

}
