package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.easynaukri.java.easynaukriApplication.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

}
