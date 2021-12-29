package com.easynaukri.java.easynaukriApplication.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.StringUtils;
import com.easynaukri.java.easynaukriApplication.dao.LocationRepository;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.JobTags;
import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.Organization;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.security.UserService;
import com.easynaukri.java.easynaukriApplication.service.AWSS3Service;
import com.easynaukri.java.easynaukriApplication.service.JobApplicationService;
import com.easynaukri.java.easynaukriApplication.service.JobCategoryService;
import com.easynaukri.java.easynaukriApplication.service.JobService;
import com.easynaukri.java.easynaukriApplication.service.OrganizationService;
import com.easynaukri.java.easynaukriApplication.service.SavedJobsApplicationService;
import com.easynaukri.java.easynaukriApplication.util.GenericResponse;
import com.easynaukri.java.easynaukriApplication.util.JobResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class JobController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private AWSS3Service service;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private JobCategoryService categoryService;
	
	@Autowired
	private SavedJobsApplicationService savedRepoService;
	
	@Autowired
	LocationRepository locationRepository;

	@PostMapping("/postJob")
	public ResponseEntity<JobResponse> postJob(@RequestPart(value = "job") String job, @RequestParam String userId,
			@RequestParam String organizationId, @RequestParam String categoryId) {
		try {
			Set<JobTags> jobTags = new HashSet<>();
			ObjectMapper mapper = new ObjectMapper();
			Job inputJob = mapper.readValue(job, Job.class);
			Optional<JobCategory> category = categoryService.getCategoryById(Integer.parseInt(categoryId));
			if (!category.isPresent()) {
				return new ResponseEntity<JobResponse>(new JobResponse("Category not found with this id"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
//			String tagString = inputJob.getTagString();
//			if(!StringUtils.isNullOrEmpty(tagString))
//			{
//				if(tagString.contains(",")) {
//				for(String tag : tagString.split(","))
//				{
//					JobTags tags = new JobTags();
//					tags.setName(tag);
//					jobTags.add(tags);
//				}
//			}
//				else
//				{
//					JobTags tags = new JobTags();
//					tags.setName(tagString);
//					jobTags.add(tags);
//				}
//			}
//			
//			inputJob.setTags(jobTags);
//			
			Optional<Organization> org = organizationService.getOrgById(Long.parseLong(organizationId));
			if (!org.isPresent()) {
				return new ResponseEntity<JobResponse>(new JobResponse("Organization not found with this id"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			Location location =   locationRepository.findByLocation(inputJob.getJobLocation());
			
			if(location==null)
			{
				Location loc = new Location();
				loc.setLocation(inputJob.getJobLocation());
				locationRepository.save(loc);
			}
			
			inputJob.setCategory(category.get());
			inputJob.setOrganization(org.get());
			inputJob.setJobPublished(new Date());
			jobService.addJob(inputJob, userId);
		} catch (Exception e) {
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in posting Job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Job successfully posted"), HttpStatus.CREATED);
	}

	@PostMapping("/getJobs")
	public ResponseEntity<JobResponse> getJobs(@RequestBody @Valid Map<String, String> criteriaMap) {
		List<Job> jobs = null;
		try {
			jobs = jobService.getJobsByCriterias(criteriaMap);
		} catch (Exception e) {
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in posting Job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, jobs), HttpStatus.CREATED);
	}

	@GetMapping("/applyJob")
	public ResponseEntity<GenericResponse> applyJob(@RequestParam("jobId") String jobId,
			@RequestParam("userId") String userId) {
		try {
			jobApplicationService.applyJob(userId, jobId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in applying job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse("Successfull job applied"), HttpStatus.CREATED);
	}

	@GetMapping("/getAllJobs")
	public ResponseEntity<JobResponse> getAllJobs(@RequestParam String pageItem,@RequestParam String pageSize) {
		Page<Job> jobs = null;
		try {
			int pageNumber = pageItem==null?0:Integer.parseInt(pageItem);
			int pageCount = pageSize==null?10:Integer.parseInt(pageSize); 
			
			Pageable paging = PageRequest.of(pageNumber, pageCount,Sort.by(Direction.DESC, "jobPublished"));
			jobs = jobService.getAllJobsByPage(paging);
		} catch (Exception e) {
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in getting Job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, jobs), HttpStatus.OK);
	}
	
	@GetMapping("/getJobDetail")
	public ResponseEntity<JobResponse> getJobDetail(@RequestParam String jobId) {
		Job job = null;
		try {
			
			job = jobService.getJobDetail(jobId);
		} catch (Exception e) {
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in getting Job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, job), HttpStatus.OK);
	}

	@PostMapping("/getFilterJobs")
	public ResponseEntity<JobResponse> getJobsByTags(@RequestBody String job) {
		Page<Job> jobs = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> searchCriteria = mapper.readValue(job, HashMap.class);
			List<String> tags = (List<String>) searchCriteria.get("tags");
			
			jobs = jobService.getJobsByTags(tags,searchCriteria);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in getting Jobs with tags with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, jobs), HttpStatus.OK);

	}
	
	@GetMapping("/getAppliedJobs")
	public ResponseEntity<JobResponse> getAppliedJobs(@RequestParam String userId,@RequestParam String pageItem,@RequestParam String pageSize) {
		Page<JobApplication> appliedJobs = null;
		try {
			int pageNumber = pageItem==null?0:Integer.parseInt(pageItem);
			int pageCount = pageSize==null?10:Integer.parseInt(pageSize); 
			Pageable paging = PageRequest.of(pageNumber, pageCount);
			appliedJobs = jobService.getAppliedJobs(userId,paging);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in getting applied Jobs with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, appliedJobs), HttpStatus.OK);

	}
	
	@GetMapping("/getApplicationRecieved")
	public ResponseEntity<JobResponse> getApplicationRecieved(@RequestParam String userId) {
		List<JobApplication> appliedJobs = null;
		try {
			
			appliedJobs = jobService.getJobsApplication(userId);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<JobResponse>(
					new JobResponse("Exception in getting applied Jobs with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", appliedJobs), HttpStatus.OK);

	}
	
	@GetMapping("/getSavedJobs")
	public ResponseEntity<?> getSavedJobs(@RequestParam String userId,@RequestParam String pageItem,@RequestParam String pageSize) {
		Page<SavedJobs> savedJobs = null;
		try {
			int pageNumber = pageItem==null?0:Integer.parseInt(pageItem);
			int pageCount = pageSize==null?10:Integer.parseInt(pageSize); 
			Pageable paging = PageRequest.of(pageNumber, pageCount);
			savedJobs = savedRepoService.getSavedJobs(userId,paging);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in getting saved jobs with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<JobResponse>(new JobResponse("Success", null, savedJobs), HttpStatus.OK);

	}
	
	@GetMapping("/getSavedJobDetail")
	public ResponseEntity<?> getSavedJobDetail(@RequestParam("jobId") String jobId,
			@RequestParam("userId") String userId) {
		SavedJobs savedJob = null;
		try {
			savedJob = savedRepoService.getSavedJobDetail(userId, jobId);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in getting saved jobs with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(savedJob);

	}
	
	@GetMapping("/saveJob")
	public ResponseEntity<GenericResponse> saveJob(@RequestParam("jobId") String jobId,
			@RequestParam("userId") String userId) {
		try {
			SavedJobs savedJob = null;
			try {
				savedJob = savedRepoService.getSavedJobDetail(userId, jobId);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(savedJob!=null)
			{
				return new ResponseEntity<GenericResponse>(new GenericResponse("Job Already Saved"), HttpStatus.ALREADY_REPORTED);
			}
			
			savedRepoService.saveJob(userId, jobId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in saving job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse("Successfull job saved"), HttpStatus.CREATED);
	}
	
	@GetMapping("/deleteSaveJob")
	public ResponseEntity<GenericResponse> deleteSaveJob(@RequestParam("savedJobId") String savedJobId) {
		try {
			savedRepoService.deleteSavedJobDetail(savedJobId);
		} catch (Exception e) {
			return new ResponseEntity<GenericResponse>(
					new GenericResponse("Exception in Deleting job with message=" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse("Deleted"), HttpStatus.CREATED);
	}
	
	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

}
