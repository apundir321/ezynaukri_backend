package com.astroenergy.java.astroenergyApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroenergy.java.astroenergyApplication.dao.RatelistRepo;
import com.astroenergy.java.astroenergyApplication.model.Ratelist;
import com.astroenergy.java.astroenergyApplication.service.RateListService;

@RestController
public class RateListController {
	@Autowired
	private RatelistRepo rateListRepo;
	@Autowired
	RateListService rateListService;

	@RequestMapping("/addRateList")
	public ResponseEntity<?> addRateList(@RequestBody Ratelist rateList) {
		try {
			Ratelist r = rateListService.addRatelist(rateList);
			return new ResponseEntity<>(r, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/getAllRateList")
	public ResponseEntity<?> getAllRateList(@RequestBody Ratelist rateList) {
		try {
			List<Ratelist> r = rateListService.getAllRatelist();
			return new ResponseEntity<>(r, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/EditRateList")
	public ResponseEntity<?> editRateList(@RequestBody Ratelist rateList) {
		try {
			// Ratelist r = rateListRepo.getById(rateList.getSno());
			// r.setCountryName(rateList.getCountryName());
			// r.setConsultationType(rateList.getConsultationType());
			// r.setRateOfValues(rateList.getRateOfValues());
			// r.setStatus(rateList.getStatus());
			Ratelist rt = rateListService.editRatelist(rateList);

			return new ResponseEntity<>(rt, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/deleteRateList/{sno}")
	public ResponseEntity<?> deleteRatelist(@RequestBody Ratelist rateList) {
		try {

			Ratelist rt = rateListService.deleteRatelist(rateList);
			return new ResponseEntity<>(rt, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
