package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.RecruiterProfile;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface UserProfileRepositoryImpl {
	
	public List<UserProfile> getUserProfiles(String location);
	
	public List<User> getUserBasedOnRole(String roleName);
	public List<RecruiterProfile> getUserBasedOnRoleParams(String roleName,Map<String, Object> searchCriteria);
	public User getUser(String recruiterProfileId) ;
	public List<UserProfile> getEmployeesBasedOnRoleParams(String roleName,Map<String, Object> searchCriteria);
}
