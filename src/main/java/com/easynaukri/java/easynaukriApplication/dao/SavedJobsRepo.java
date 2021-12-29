package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.User;

@Repository
public interface SavedJobsRepo extends JpaRepository<SavedJobs,Long>,SavedJobsCustomRepo{
	
	public Page<SavedJobs> findAllByApplicantAndStatus(User user,String status,Pageable page);

}
