package com.easynaukri.java.easynaukriApplication.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easynaukri.java.easynaukriApplication.dao.JobApplicationRepo;
import com.easynaukri.java.easynaukriApplication.dao.JobCategoryRepo;
import com.easynaukri.java.easynaukriApplication.dao.JobRepository;
import com.easynaukri.java.easynaukriApplication.dao.JobTagsRepo;
import com.easynaukri.java.easynaukriApplication.dao.SavedJobsRepo;
import com.easynaukri.java.easynaukriApplication.dao.UserRepository;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.JobTags;
import com.easynaukri.java.easynaukriApplication.model.User;

@Service
public class JobService {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	JobCategoryRepo categoryRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JobTagsRepo jobTagsRepo;
	
	@Autowired
	JobApplicationRepo jobApplicationRepo;
	
	@Autowired
	SavedJobsRepo savedJobsRepo;
	

	public void addJob(Job job,String userId) throws Exception {
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent()) {
				
			Set<JobTags> tags = job.getTags();
			if(tags!=null && tags.size()>0)
			{
				for(JobTags tag : tags)
				{
					JobTags jobTag = jobTagsRepo.findByName(tag.getName());
					if(jobTag==null)
					{
						JobTags savedJobTags = jobTagsRepo.save(tag);
						tag.setId(savedJobTags.getId());
					}
					else
					{
						tag.setId(jobTag.getId());
					}
				}
			}
			job.setPostedBy(user.get());
			jobRepository.save(job);
			}
			else {
				throw new GenericException("User is not present for this userId");
			}
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}

	public List<Job> getJobsByCategory(String category) throws Exception {
		try {
			Optional<JobCategory> jobCategory = categoryRepo.findById(Integer.parseInt(category));

			if (jobCategory.isPresent()) {
				return jobRepository.findJobByCategory(jobCategory.get());
			} else {
				throw new GenericException("Job category is not available for this ID");
			}

		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public Page<Job> getJobsByTags(List<String> tags,Map<String, Object> searchCriteria) throws Exception {
		try {
			
				return jobRepository.findJobsByTags(tags,searchCriteria);
			
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public List<Job> getAllJobs() throws Exception {
		try {
			
				return jobRepository.findAll();
			
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}

	public Page<Job> getAllJobsByPage(Pageable page) throws Exception {
		try {
			
				return jobRepository.findAll(page);
			
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public List<Job> getJobsByCriterias(Map<String, String> criteriaMap) throws Exception {
		try {
			return jobRepository.findJobsByJobCriterias(criteriaMap);
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public Page<JobApplication> getAppliedJobs(String userId,Pageable page)throws Exception
	{
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent()) {
			return jobApplicationRepo.findAllByApplicant(user.get(),page);
			}
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		return null;
	}
	
	public Job getJobDetail(String jobId)throws Exception
	{
		try {
			
			return jobRepository.findById(Long.parseLong(jobId)).get();
			
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}

}
