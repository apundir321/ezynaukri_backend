package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.RecruiterProfile;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface RecruiterProfileRepository extends CrudRepository<RecruiterProfile, Integer> {
	
	

}
