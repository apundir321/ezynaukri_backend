package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.User;

@Repository
public interface SavedJobsRepo extends JpaRepository<SavedJobs,Integer>,SavedJobsCustomRepo{
	
	public List<SavedJobs> findAllByApplicant(User user);

}
