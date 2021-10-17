package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Skills;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
public interface SkillsRepository extends CrudRepository<Skills, Integer> {
	
	public Skills findByName(String name);

}
