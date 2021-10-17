package com.easynaukri.java.easynaukriApplication.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.User;

@Repository
@Transactional
public class SavedJobsCustomRepoImpl implements SavedJobsCustomRepo{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JobRepository jobRepo;
	

	@PersistenceContext
	EntityManager em;
	
	@Override
	public SavedJobs findSavedJobByUserIdAndJobId(String userId, String jobId) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<SavedJobs> cq = cb.createQuery(SavedJobs.class);

	    Root<SavedJobs> job = cq.from(SavedJobs.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (userId != null) {
	    	Optional<User> user = userRepository.findById(Long.parseLong(userId));
	        predicates.add(cb.equal(job.get("applicant"),  user.get()));
	    }
	    if (jobId != null) {
	    	Optional<Job> savedJob = jobRepo.findById(Long.parseLong(jobId));
	        predicates.add(cb.equal(job.get("job"), savedJob.get()));
	    }
	   
	    return em.createQuery(cq).getSingleResult();

	}

}
