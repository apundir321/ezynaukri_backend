package com.easynaukri.java.easynaukriApplication.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Organization;
import com.easynaukri.java.easynaukriApplication.service.AWSS3Service;
import com.easynaukri.java.easynaukriApplication.service.OrganizationService;
import com.easynaukri.java.easynaukriApplication.util.GenericResponse;
import com.easynaukri.java.easynaukriApplication.util.JobResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private AWSS3Service service;
	
	@PostMapping("/addOrganization")
	public ResponseEntity<?> addOrganization(@RequestPart(value= "file" ,required = true) final MultipartFile multipartFile,@RequestParam(value = "orgData") String org,@RequestParam String categoryId)
	{
		Organization organization = null;
		File file = null;
		if(multipartFile!=null) {
		try {
			file = convertMultiPartFileToFile(multipartFile);
			service.uploadGenericFile(multipartFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		try {
			organization = mapper.readValue(org, Organization.class);
			organization.setOrgProfileImage(file.getName());
			organization = organizationService.addOrganization(organization,categoryId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(organization,HttpStatus.OK);
	}
	
	@GetMapping("/getOrganizations")
	public ResponseEntity<?> getOrganizations()
	{
		List<Organization> organizations = null;
		try {
			organizations = organizationService.getOrganizations();
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(organizations,HttpStatus.OK);
		
	}
	
	@GetMapping("/getOrgDetail")
	public ResponseEntity<?> getOrgDetail(@RequestParam String orgId)
	{
		Organization organization = null;
		try {
			Optional<Organization> org = organizationService.getOrgById(Long.parseLong(orgId));
			if(org.isPresent()) {
				organization = org.get();
			}
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(organization,HttpStatus.OK);
	}
	
	@PostMapping("/getOrgsByCriteria")
	public ResponseEntity<?> getOrgsByCriteria(@RequestBody String orgData) {
		List<Organization> organizations = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> searchCriteria = mapper.readValue(orgData, HashMap.class);
			
			organizations = organizationService.getOrganizationsByCriteria(searchCriteria);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Error in fetching org data from getOrgsByCriteria method"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Organization>>(organizations, HttpStatus.OK);

	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) throws Exception {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			throw ex;
		}
		return file;
	}
}
