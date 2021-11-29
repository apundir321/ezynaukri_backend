package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.astroenergy.java.astroenergyApplication.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

}
