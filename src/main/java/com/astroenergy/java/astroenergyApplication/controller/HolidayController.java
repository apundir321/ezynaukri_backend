package com.astroenergy.java.astroenergyApplication.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroenergy.java.astroenergyApplication.model.Day;
import com.astroenergy.java.astroenergyApplication.model.Holiday;
import com.astroenergy.java.astroenergyApplication.model.TimeSlot;
import com.astroenergy.java.astroenergyApplication.service.HolidayService;

@RestController
public class HolidayController {
	
	private HolidayService holidayService;
	
	
	@PostMapping("/saveHoliday")
	public ResponseEntity<?> saveHoliday(@RequestBody Holiday holiday)
	{
		try {
			Holiday savedHoliday = holidayService.saveHoliday(holiday);
			return new ResponseEntity<Holiday>(savedHoliday, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in saving Holiday", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("/editHoliday")
	public ResponseEntity<?> editHoliday(@RequestBody Holiday holiday)
	{
		try {
			Holiday savedHoliday = holidayService.saveHoliday(holiday);
			return new ResponseEntity<Holiday>(savedHoliday, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in updating Holiday", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/getHolidays")
	public ResponseEntity<?> getHolidays()
	{
		try {
			List<Holiday> holidays = holidayService.getHolidays();
			return new ResponseEntity<List<Holiday>>(holidays, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in getting holidays", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/getHolidayDetail")
	public ResponseEntity<?> getHolidays(@RequestParam long id)
	{
		try {
			Holiday holiday = holidayService.getHolidayDetail(id);
			return new ResponseEntity<Holiday>(holiday, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in getting holidays", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("/addTimeSlots")
	public ResponseEntity<?> addTimeSlots(@RequestParam long id,@RequestBody Set<TimeSlot> timeslots)
	{
		try {
			Holiday holiday = holidayService.addTimeSlotsToHoliday(timeslots, id);
			return new ResponseEntity<Holiday>(holiday, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in adding timeslot", HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
	}

}
