package com.astroenergy.java.astroenergyApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroenergy.java.astroenergyApplication.dao.CountryRepo;
import com.astroenergy.java.astroenergyApplication.model.Country;
import com.astroenergy.java.astroenergyApplication.service.CountryServce;





@RestController
public class CountryController {
@Autowired
private CountryRepo countryRepo;
	@Autowired
	CountryServce countryService;

@RequestMapping("/AddCountry")
public ResponseEntity<?> addCountry(@RequestBody Country country) {
	try {
	Country c = countryService.addCountry(country);
	 return new ResponseEntity<Country>(c, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("/AllCountries")
public ResponseEntity<?> allCountry() {
	try {
	List<Country> c= countryService.getAll();
	 return new ResponseEntity<>(c, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping("/UpdateCountry")
public ResponseEntity<?> updateCountry(@RequestBody Country country) {
	try {
	//Country c = countryRepo.getById(country.getSno());
	//c.setCode(country.getCode());
	//c.setName(country.getName());
//	c.setRemarks(country.getRemarks());
	//c.setStatus(country.getStatus());
	Country c=countryService.updateCountry(country);
	 return new ResponseEntity<>(c, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping("/deleteCountry")
public ResponseEntity<?> deleteCountry(@RequestBody Country country) {
	try {
	
	Country ct=countryService.deleteCountry(country);
	 return new ResponseEntity<>(ct, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}
