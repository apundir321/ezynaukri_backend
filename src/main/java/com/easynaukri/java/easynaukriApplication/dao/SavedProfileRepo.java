package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.SavedProfile;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface SavedProfileRepo extends JpaRepository<SavedProfile,Long>{
	
	List<SavedProfile> findByRecruiterUser(User user);
	
	public SavedProfile findByRecruiterUserAndApplicant(User recruiterUser,UserProfile applicant);

}
