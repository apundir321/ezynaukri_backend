package com.easynaukri.java.easynaukriApplication.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.datapipeline.model.Query;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.RecruiterProfile;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;

@Repository
@Transactional
public class UserProfileRepositoryImplService implements UserProfileRepositoryImpl{
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	JobCategoryRepo jobCategoryRepo;
	
	@Autowired
	RecruiterProfileRepository recruiterProfileRepo;

	@Override
	public List<UserProfile> getUserProfiles(String locationValue) {
		
//		Location fetchedLocation = locationRepository.findByLocation(location);
		String qlString = "SELECT profile FROM UserProfile profile JOIN profile.prefferedLocation loc WHERE loc.location IN (:loc)"; 
		List<String> empNumbers = Arrays.asList("Gurgaon", "chennai");
		TypedQuery<UserProfile> q = em.createQuery(qlString, UserProfile.class);
		
		q.setParameter("loc", empNumbers);
		List<UserProfile> actual = q.getResultList();
		
		return actual;
	}
	
	@Override
	public List<User> getUserBasedOnRole(String roleName) {
		
		String qlString = "SELECT u FROM User u JOIN u.roles role WHERE role.name IN (:r)"; 
		TypedQuery<User> q = em.createQuery(qlString, User.class);
		
		q.setParameter("r", roleName);
		List<User> actual = q.getResultList();
		
		return actual;
	}
	
	@Override
	public User getUser(String recruiterProfileId) {
		
		String qlString = "SELECT u FROM User u WHERE u.recruiterProfile = (:r)"; 
		TypedQuery<User> q = em.createQuery(qlString, User.class);
		
		Optional<RecruiterProfile> profile = recruiterProfileRepo.findById(Integer.parseInt(recruiterProfileId));
		if(profile.isPresent()) {
			q.setParameter("r", profile.get());	
		}
		
		List<User> actual = q.getResultList();
		
		return actual.get(0);
	}
	
	@Override
	public List<RecruiterProfile> getUserBasedOnRoleParams(String roleName,Map<String, Object> searchCriteria) {
		
		String qlString = "SELECT DISTINCT profile FROM RecruiterProfile profile JOIN profile.tags tag JOIN profile.prefferedLocation prefferedLocation "; 
		
		if (searchCriteria.get("tags") != null) {
			 qlString += " WHERE tag.name IN (:jobTags)";
		 }
//		else
//		{
//			qlString += " WHERE profile.workExperience > 0";
//		}
		if (searchCriteria.get("locations") != null) {
			 qlString += " AND prefferedLocation.location IN (:locations)";
		 }
		if (searchCriteria.get("category") != null) {
			 qlString += " AND profile.category = (:categoryId)";
		 }
		TypedQuery<RecruiterProfile> q = em.createQuery(qlString, RecruiterProfile.class);
		
		if (searchCriteria.get("tags") != null) {
			q.setParameter("jobTags", searchCriteria.get("tags"));
		}
		 if (searchCriteria.get("locations") != null) {
			 q.setParameter("locations", searchCriteria.get("locations"));
		 }
		 if (searchCriteria.get("category") != null) {
		    	Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt((String)searchCriteria.get("category")));
		    	q.setParameter("categoryId",category.get() );
		    }
//		q.setParameter("r", "ROLE_RECRUITER");
		List<RecruiterProfile> actual = q.getResultList();
		
		return actual;
	}
	
	
	@Override
	public List<UserProfile> getEmployeesBasedOnRoleParams(String roleName,Map<String, Object> searchCriteria) {
		
		String qlString = "SELECT DISTINCT profile FROM UserProfile profile"; 
		
		if (searchCriteria.get("tags") != null) {
			 qlString += " WHERE tag.name IN (:jobTags)";
		 }
		if (searchCriteria.get("locations") != null) {
			 qlString += " AND prefferedLocation.location IN (:locations)";
		 }
		
		if (searchCriteria.get("minExperience") != null) {
			 qlString += " AND profile.workExperience >= (:minExperience)";
		 }
		
		if (searchCriteria.get("maxExperience") != null) {
			 qlString += " AND profile.workExperience <= (:maxExperience)";
		 }
		if (searchCriteria.get("category") != null) {
			 qlString += " AND profile.category = (:categoryId)";
		 }
		TypedQuery<UserProfile> q = em.createQuery(qlString, UserProfile.class);
		
		if (searchCriteria.get("tags") != null) {
			q.setParameter("jobTags", searchCriteria.get("tags"));
		}
		 if (searchCriteria.get("locations") != null) {
			 q.setParameter("locations", searchCriteria.get("locations"));
		 }
		 
		 if (searchCriteria.get("minExperience") != null) {
			 q.setParameter("minExperience", searchCriteria.get("minExperience"));
		 }
		
		if (searchCriteria.get("maxExperience") != null) {
			 q.setParameter("maxExperience", searchCriteria.get("maxExperience"));
		 }
		 if (searchCriteria.get("category") != null) {
		    	Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt((String)searchCriteria.get("category")));
		    	q.setParameter("categoryId",category.get() );
		    }
//		q.setParameter("r", "ROLE_RECRUITER");
		List<UserProfile> actual = q.getResultList();
		
		return actual;
	}

}
