package com.easynaukri.java.easynaukriApplication.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.service.JobCategoryService;
import com.easynaukri.java.easynaukriApplication.util.GenericResponse;

@RestController
public class CategoryController {
	
	@Autowired
	private JobCategoryService jobCategoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody JobCategory category)
	{
		JobCategory jobCategory = null;
		try {
			jobCategory = jobCategoryService.addCategory(category);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(jobCategory,HttpStatus.OK);
	}
	
	
	@GetMapping("/getCategories")
	public ResponseEntity<?> getCategories()
	{
		List<JobCategory> categories = null;
		try {
			categories = jobCategoryService.getCategories();
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(categories,HttpStatus.OK);
		
	}
	
}
