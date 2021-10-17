package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Education;
import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.Skills;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface EducationRepository extends CrudRepository<Education, Integer> {

	
	
	
}
