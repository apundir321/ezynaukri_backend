package com.astroenergy.java.astroenergyApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroenergy.java.astroenergyApplication.dao.TimeSlotRepo;
import com.astroenergy.java.astroenergyApplication.model.Day;
import com.astroenergy.java.astroenergyApplication.model.TimeSlot;

@Service
public class TimeSlotService {
	
	@Autowired
	TimeSlotRepo timeSlotRepo;
	
	
	public TimeSlot saveTimeSlot(TimeSlot timeSlot)throws Exception
	{
		try {
			return timeSlotRepo.save(timeSlot);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	
	public TimeSlot getTimeSlotDetail(long id) throws Exception
	{
		Optional<TimeSlot> timeSlot = null;
		try {
			timeSlot = timeSlotRepo.findById(id);
			if(timeSlot.isPresent())
			{
				return timeSlot.get();
			}else
			{
				throw new Exception("Time slot not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public TimeSlot updateTimeSlot(TimeSlot timeSlot)throws Exception
	{
		try {
			return timeSlotRepo.save(timeSlot);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	
	public TimeSlot addDaysToTimeSlot(Set<Day> days,long id) throws Exception
	{
		Optional<TimeSlot> timeSlot = null;
		try {
			timeSlot = timeSlotRepo.findById(id);
			if(timeSlot.isPresent())
			{
				TimeSlot slot =  timeSlot.get();
				slot.setDays(days);
				return timeSlotRepo.save(slot);
			}else
			{
				throw new Exception("Time slot not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}
	
	public List<TimeSlot> getSlots()
	{
		return (List<TimeSlot>) timeSlotRepo.findAll();
	}

}
