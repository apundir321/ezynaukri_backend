package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.Skills;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	public Location findByLocation(String location);
	
	
	
}
