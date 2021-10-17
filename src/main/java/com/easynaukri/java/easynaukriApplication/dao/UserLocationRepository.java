package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
