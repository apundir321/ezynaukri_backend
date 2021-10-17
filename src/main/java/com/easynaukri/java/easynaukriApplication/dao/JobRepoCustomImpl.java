package com.easynaukri.java.easynaukriApplication.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Organization;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
@Transactional
public class JobRepoCustomImpl implements JobRepoCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	JobCategoryRepo jobCategoryRepo;
	
	@Autowired
	OrganizationRepository organizationRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Job> findJobsByJobCriterias(Map<String, String> searchCriteria) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Job> cq = cb.createQuery(Job.class);
		    
		    Root<Job> job = cq.from(Job.class);
		    List<Predicate> predicates = new ArrayList<>();
		    
		    if (searchCriteria.get("category") != null) {
		    	Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt(searchCriteria.get("category")));
		        predicates.add(cb.equal(job.get("category"), category.get()));
		    }
		    if (searchCriteria.get("posted_by") != null) {
		    	Optional<User> user = userRepo.findById(Long.parseLong(searchCriteria.get("posted_by")));
		        predicates.add(cb.equal(job.get("postedBy"), user.get()));
		    }
		    if (searchCriteria.get("organization") != null) {
		    	Optional<Organization> org = organizationRepo.findById(Long.parseLong(searchCriteria.get("organization")));
		        predicates.add(cb.equal(job.get("organization"), org.get()));
		    }
		    if(searchCriteria.get("minExperience") != null)
		    {
		    	predicates.add(cb.greaterThanOrEqualTo(job.get("minExperience"),searchCriteria.get("minExperience")));
		    }
		    if(searchCriteria.get("maxExperience") != null)
		    {
		    	predicates.add(cb.lessThanOrEqualTo(job.get("maxExperience"),searchCriteria.get("maxExperience")));
		    }
		    if(searchCriteria.get("minSalary") != null)
		    {
		    	predicates.add(cb.greaterThanOrEqualTo(job.get("minSalary"),searchCriteria.get("minSalary")));
		    }
		    if(searchCriteria.get("maxSalary") != null)
		    {
		    	predicates.add(cb.lessThanOrEqualTo(job.get("maxSalary"),searchCriteria.get("maxSalary")));
		    }
		    if(searchCriteria.get("jobPublished") != null)
		    {
		    	try {
					Date jobPublished=new SimpleDateFormat("yyyy-MM-dd").parse(searchCriteria.get("jobPublished"));
					predicates.add(cb.greaterThanOrEqualTo(job.get("jobPublished"),jobPublished));
		    	} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    cq.where(predicates.toArray(new Predicate[0]));

		    return em.createQuery(cq).getResultList();
	}
	
	@Override
	public List<Organization> findOrgsByCriteria(Map<String, Object> searchCriteria) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
		    
		    Root<Organization> org = cq.from(Organization.class);
		    List<Predicate> predicates = new ArrayList<>();
		    
		    if (searchCriteria.get("category") != null) {
		    	Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt((String)searchCriteria.get("category")));
		        predicates.add(cb.equal(org.get("category"), category.get()));
		    }
		    if (searchCriteria.get("location") != null) {
		    	
		    	Expression<String> exp = org.get("location");
		    	Predicate predicate = exp.in(searchCriteria.get("location"));
		        predicates.add(predicate);
		    }
		    cq.where(predicates.toArray(new Predicate[0]));

		    return em.createQuery(cq).getResultList();
	}


	@Override
	public Page<Job> findJobsByTags(List<String> tags,Map<String, Object> searchCriteria) {
	
		
		String qlString = "SELECT DISTINCT job FROM Job job JOIN job.tags tag "; 

		
		
		if (searchCriteria.get("tags") != null) {
			 qlString += "WHERE tag.name IN (:jobTags)";
		 }
		else
		{
			qlString += "WHERE job.minExperience >= 0";
		}
		if (searchCriteria.get("category") != null) {
			 qlString += "AND job.category = (:categoryId)";
		 }
		 if (searchCriteria.get("organization") != null) {
			 qlString += "AND job.organization = (:organizationId)";
		 }
		 if (searchCriteria.get("jobPublished") != null) {
			 qlString += "AND job.jobPublished >= (:jobPublished)";
		 }
		 if (searchCriteria.get("locations") != null) {
			 qlString += "AND job.jobLocation IN (:locations)";
		 }
		 
		 qlString += "AND job.minExperience >= (:mExperience)";
		 
		 
		 qlString += "AND job.maxExperience <= (:maxExperience)";
		 
		 if (searchCriteria.get("maxExperience") != null) {
			 qlString += "AND job.maxExperience <= (:maxExperience)";
		 }
		 if (searchCriteria.get("minSalary") != null) {
			 qlString += " AND job.minSalary <= (:minSalary)";
		 }
		 if (searchCriteria.get("maxSalary") != null) {
			 qlString += "  AND job.maxSalary <= (:maxSalary)";
		 }
		 
		TypedQuery<Job> q = em.createQuery(qlString, Job.class);
		if (searchCriteria.get("tags") != null) {
			q.setParameter("jobTags", tags);
		}
		 if (searchCriteria.get("locations") != null) {
			 q.setParameter("locations", searchCriteria.get("locations"));
		 }
		 if (searchCriteria.get("category") != null) {
		    	Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt((String)searchCriteria.get("category")));
		    	q.setParameter("categoryId",category.get() );
		    }
		    if (searchCriteria.get("organization") != null) {
		    	Optional<Organization> org = organizationRepo.findById(Long.parseLong((String)searchCriteria.get("organization")));
		    	q.setParameter("organizationId",org.get() );
		    }
		 	if(searchCriteria.get("minExperience") != null)
		    {
			 q.setParameter("mExperience",Integer.parseInt((String)searchCriteria.get("minExperience")) );
		    }
		 	else
		    {
		    	q.setParameter("mExperience",Integer.MIN_VALUE );
		    }
		    if(searchCriteria.get("maxExperience") != null)
		    {
		    	q.setParameter("maxExperience",Integer.parseInt((String)searchCriteria.get("maxExperience")) );
		    }else
		    {
		    	q.setParameter("maxExperience",Integer.MAX_VALUE );
		    }
		    if(searchCriteria.get("minSalary") != null)
		    {
		    	q.setParameter("minSalary",Integer.parseInt((String)searchCriteria.get("minSalary")) );
		    }
		    if(searchCriteria.get("maxSalary") != null)
		    {
		    	q.setParameter("maxSalary",Integer.parseInt((String)searchCriteria.get("maxSalary")) );
		    }
		    if(searchCriteria.get("jobPublished") != null)
		    {
		    	try {
					Date jobPublished=new SimpleDateFormat("yyyy-MM-dd").parse((String)searchCriteria.get("jobPublished"));
					q.setParameter("jobPublished",jobPublished );
		    	} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
			int pageNumber = 0;
			int pageCount = 10; 
			Pageable paging = PageRequest.of(pageNumber, pageCount);
		List<Job> actual = q.getResultList();
		Page<Job> jobPage = new  PageImpl<>(actual,paging,actual.size());
		return jobPage;
	}

	
}
