package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.SavedProfile;
import com.easynaukri.java.easynaukriApplication.model.User;

@Repository
public interface SavedProfileRepo extends JpaRepository<SavedProfile,Integer>{
	
	List<SavedProfile> findByRecruiterUser(User user);

}
