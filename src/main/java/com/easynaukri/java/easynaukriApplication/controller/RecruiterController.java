package com.easynaukri.java.easynaukriApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easynaukri.java.easynaukriApplication.model.SavedProfile;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;
import com.easynaukri.java.easynaukriApplication.service.UserProfileService;
import com.easynaukri.java.easynaukriApplication.util.GenericResponse;

@RestController
public class RecruiterController {
	
	@Autowired
	UserProfileService userProfileService;
	
	@RequestMapping(value = "/getSavedProfiles", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@RequestParam String userId)
			throws Exception {
		List<SavedProfile> profiles = null;
		try {
			profiles = userProfileService.getSavedProfiles(userId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse("Exception in getting Saved Profiles="+e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<SavedProfile>>(profiles,HttpStatus.OK);
	}
	
	@GetMapping("/saveProfile")
	public ResponseEntity<GenericResponse> saveJob(@RequestParam("userId") String userId,
			@RequestParam("recruiterId") String recruiterId) {
		try {
			userProfileService.saveProfile(userId, recruiterId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in saving job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse("Successfull Profile saved"), HttpStatus.CREATED);
	}

}
