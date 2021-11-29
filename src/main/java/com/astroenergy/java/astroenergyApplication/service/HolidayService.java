package com.astroenergy.java.astroenergyApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroenergy.java.astroenergyApplication.dao.HolidayRepo;
import com.astroenergy.java.astroenergyApplication.model.Day;
import com.astroenergy.java.astroenergyApplication.model.Holiday;
import com.astroenergy.java.astroenergyApplication.model.TimeSlot;

@Service
public class HolidayService {
	
	@Autowired
	private HolidayRepo holidayRepo;

	public Holiday saveHoliday(Holiday holiday) throws Exception {
		try {
			return holidayRepo.save(holiday);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public Holiday editHoliday(Holiday holiday) throws Exception {
		try {
			return holidayRepo.save(holiday);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public Holiday deleteHoliday(Holiday holiday) throws Exception {
		try {
			return holidayRepo.save(holiday);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public Optional<Holiday> findHoliday(long id) throws Exception {
		try {
			return holidayRepo.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public List<Holiday> getHolidays() throws Exception {
		try {
			return (List<Holiday>) holidayRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public Holiday addTimeSlotsToHoliday(Set<TimeSlot> timeSlots,long id) throws Exception
	{
		Optional<Holiday> holiday = null;
		try {
			holiday = holidayRepo.findById(id);
			if(holiday.isPresent())
			{
				Holiday savedHoliday =  holiday.get();
				savedHoliday.setTimeSlots(timeSlots);
				return holidayRepo.save(savedHoliday);
			}else
			{
				throw new Exception("Holiday not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}

	public Holiday getHolidayDetail(long id)throws Exception {
		// TODO Auto-generated method stub
		Optional<Holiday> holiday = null;
		try {
			holiday = holidayRepo.findById(id);
			if(holiday.isPresent())
			{
				Holiday savedHoliday =  holiday.get();
				return savedHoliday;
			}else
			{
				throw new Exception("Holiday not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	

}
