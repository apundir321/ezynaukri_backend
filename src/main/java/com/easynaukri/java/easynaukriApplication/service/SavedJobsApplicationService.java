package com.easynaukri.java.easynaukriApplication.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazonaws.services.appstream.model.Application;
import com.easynaukri.java.easynaukriApplication.dao.JobRepository;
import com.easynaukri.java.easynaukriApplication.dao.SavedJobsRepo;
import com.easynaukri.java.easynaukriApplication.dao.UserRepository;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.User;

@Service
public class SavedJobsApplicationService {

	@Autowired
	SavedJobsRepo savedJobsRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JobRepository jobRepository;
	

	public void saveJob(String userId,String jobId)throws Exception
	{
		try {
		Optional<User> user = userRepository.findById(Long.parseLong(userId));
		if(user.isPresent())
		{
			Optional<Job> job = jobRepository.findById(Long.parseLong(jobId));
			if(!job.isPresent())
			{
				throw new GenericException("Job Id not found");
			}
			SavedJobs application = new SavedJobs();
			application.setJob(job.get());
			application.setApplicant(user.get());
			application.setApplicationDate(new Date());
			application.setStatus("open");
			savedJobsRepo.save(application);
		}
		else
		{
			throw new GenericException("User Id not found");
		}
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		
		
	}
	
	public Page<SavedJobs> getSavedJobs(String userId,Pageable page)throws Exception
	{
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent()) {
			return savedJobsRepo.findAllByApplicantAndStatus(user.get(),"open",page);
			}
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		return null;
	}
	
	public SavedJobs getSavedJobDetail(String userId,String jobId)throws Exception
	{
		try {
			return savedJobsRepo.findSavedJobByUserIdAndJobId(userId, jobId);
			
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public void deleteSavedJobDetail(String savedJobId)throws Exception
	{
		try {
			SavedJobs savedJob = savedJobsRepo.findById(Long.parseLong(savedJobId)).get();
			savedJob.setStatus("deleted");
			savedJobsRepo.save(savedJob);
			
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}

}
