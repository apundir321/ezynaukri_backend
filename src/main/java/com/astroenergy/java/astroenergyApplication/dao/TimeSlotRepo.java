package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.astroenergy.java.astroenergyApplication.model.TimeSlot;
import com.astroenergy.java.astroenergyApplication.model.UserProfile;

public interface TimeSlotRepo  extends CrudRepository<TimeSlot, Long> {

}
