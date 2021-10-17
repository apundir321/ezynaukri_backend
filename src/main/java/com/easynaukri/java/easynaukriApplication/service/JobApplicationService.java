package com.easynaukri.java.easynaukriApplication.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easynaukri.java.easynaukriApplication.dao.JobApplicationRepo;
import com.easynaukri.java.easynaukriApplication.dao.JobRepository;
import com.easynaukri.java.easynaukriApplication.dao.UserRepository;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.User;

@Service
public class JobApplicationService {

	@Autowired
	JobApplicationRepo jobApplicationRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JobRepository jobRepository;

	public void applyJob(String userId,String jobId)throws Exception
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
			JobApplication application = new JobApplication();
			application.setJob(job.get());
			application.setApplicant(user.get());
			application.setApplicationDate(new Date());
			jobApplicationRepo.save(application);
		}
		else
		{
			throw new GenericException("User Id not found");
		}
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		
		
	}

}
