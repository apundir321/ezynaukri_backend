package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.astroenergy.java.astroenergyApplication.model.Appointment;
import com.astroenergy.java.astroenergyApplication.model.UserProfile;

public interface AppointMentRepo   extends CrudRepository<Appointment, Long> {

}
