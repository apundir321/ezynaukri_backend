package com.easynaukri.java.easynaukriApplication.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easynaukri.java.easynaukriApplication.dao.JobCategoryRepo;
import com.easynaukri.java.easynaukriApplication.dao.LocationRepository;
import com.easynaukri.java.easynaukriApplication.dao.RecruiterProfileRepository;
import com.easynaukri.java.easynaukriApplication.dao.SavedProfileRepo;
import com.easynaukri.java.easynaukriApplication.dao.SkillsRepository;
import com.easynaukri.java.easynaukriApplication.dao.UserProfileRepository;
import com.easynaukri.java.easynaukriApplication.dao.UserRepository;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.Education;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.RecruiterProfile;
import com.easynaukri.java.easynaukriApplication.model.SavedJobs;
import com.easynaukri.java.easynaukriApplication.model.SavedProfile;
import com.easynaukri.java.easynaukriApplication.model.Skills;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;
import com.easynaukri.java.easynaukriApplication.model.WorkExperience;

@Service
public class UserProfileService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	RecruiterProfileRepository recruiterProfileRepository;
	
	@Autowired
	SkillsRepository skillsRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	JobCategoryRepo jobCategoryRepo;
	
	@Autowired
	SavedProfileRepo savedProfileRepo;
	

	public UserProfile updateUserProfile(String userId, UserProfile profile) throws Exception {
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if (user.isPresent()) {

				User profileUser = user.get();
				if (profileUser.getUserProfile() == null) {
					profileUser.setUserProfile(profile);
					return userRepository.save(profileUser).getUserProfile();
				} else {
					UserProfile savedUserProfile = user.get().getUserProfile();
					profile.setId(savedUserProfile.getId());
					profile.setEducations(savedUserProfile.getEducations());
					profile.setWorkExperiences(savedUserProfile.getWorkExperiences());
					for(Skills skill : profile.getTags())
					{
						Skills fetchedSkill = skillsRepository.findByName(skill.getName());
						if(fetchedSkill==null)
						{
							Skills savedSkill = skillsRepository.save(skill);
							skill.setId(savedSkill.getId());
						}
						else
						{
							skill.setId(fetchedSkill.getId());
						}
					}
					
					for(Location location : profile.getPrefferedLocation())
					{
						Location fetchedLocation = locationRepository.findByLocation(location.getLocation());
						if(fetchedLocation==null)
						{
							Location savedLocation = locationRepository.save(location);
							location.setId(savedLocation.getId());
						}
						else
						{
							location.setId(fetchedLocation.getId());
						}
					}
					return userProfileRepository.save(profile);
				}

			} else {
				throw new GenericException("User id is not available for this ID=" + userId);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public RecruiterProfile updateRecruiterProfile(String userId, RecruiterProfile profile,String categoryId) throws Exception {
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if (user.isPresent()) {

				User profileUser = user.get();
				if (profileUser.getRecruiterProfile() == null) {
					profileUser.setRecruiterProfile(profile);
					return userRepository.save(profileUser).getRecruiterProfile();
				} else {
					RecruiterProfile savedUserProfile = user.get().getRecruiterProfile();
					profile.setId(savedUserProfile.getId());
					 profile.setFirstName(savedUserProfile.getFirstName());
					 profile.setLastName(savedUserProfile.getLastName());
					 profile.setEmail(savedUserProfile.getEmail());
					for(Skills skill : profile.getTags())
					{
						Skills fetchedSkill = skillsRepository.findByName(skill.getName());
						if(fetchedSkill==null)
						{
							Skills savedSkill = skillsRepository.save(skill);
							skill.setId(savedSkill.getId());
						}
						else
						{
							skill.setId(fetchedSkill.getId());
						}
					}
					
					for(Location location : profile.getPrefferedLocation())
					{
						Location fetchedLocation = locationRepository.findByLocation(location.getLocation());
						if(fetchedLocation==null)
						{
							Location savedLocation = locationRepository.save(location);
							location.setId(savedLocation.getId());
						}
						else
						{
							location.setId(fetchedLocation.getId());
						}
					}
					Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt(categoryId));
			        profile.setCategory(category.get());
		       
					return recruiterProfileRepository.save(profile);
				}

			} else {
				throw new GenericException("User id is not available for this ID=" + userId);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	
	public UserProfile getUserProfile(String userId)throws Exception
	{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent())
			{
				UserProfile profile = user.get().getUserProfile();
				if(profile==null)
				{
					throw new GenericException("Profile is not yet completed by the user");
				}
				return profile;
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public List<Location> getLocations() throws Exception{
		try {
			List<Location> locations = (List<Location>) locationRepository.findAll();
			if(locations!=null && locations.size()>0)
			{
				return locations;
			} else {
				throw new GenericException("Unable to find locations");
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}

	}
	
	public UserProfile addEducation(Education education,String userId) throws Exception{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent())
			{
				UserProfile profile = user.get().getUserProfile();
				if(profile==null)
				{
					throw new GenericException("Profile is not yet completed by the user");
				}
				profile.getEducations().add(education);
				return userProfileRepository.save(profile);
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public UserProfile addWorkExperience(WorkExperience workExperience,String userId) throws Exception{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent())
			{
				UserProfile profile = user.get().getUserProfile();
				if(profile==null)
				{
					throw new GenericException("Profile is not yet completed by the user");
				}
				profile.getWorkExperiences().add(workExperience);
				return userProfileRepository.save(profile);
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}

	public UserProfile updateTags(Set<Skills> skills,String userId) throws Exception{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent())
			{
				UserProfile profile = user.get().getUserProfile();
				if(profile==null)
				{
					throw new GenericException("Profile is not yet completed by the user");
				}
				for(Skills skill : skills)
				{
					Skills fetchedSkill = skillsRepository.findByName(skill.getName());
					if(fetchedSkill==null)
					{
						Skills savedSkill = skillsRepository.save(skill);
						skill.setId(savedSkill.getId());
					}
					else
					{
						skill.setId(fetchedSkill.getId());
					}
				}
				
				profile.setTags(skills);
				return userProfileRepository.save(profile);
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public User getUser(String userId) throws Exception{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if(user.isPresent())
			{
				User savedUser = user.get();
				if(savedUser==null)
				{
					throw new GenericException("No user found for this ID");
				}
				return savedUser;
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public List<User> getUsers() throws Exception{
		try {
			List<User> userList = new ArrayList<User>();
			Iterable<User> users = userRepository.findAll();
			users.forEach(userList::add);
			if(userList.size()>0)
			{
				return userList;
			}
			else
			{
				throw new GenericException("Didn't find any users");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	public List<SavedProfile> getSavedProfiles(String recruiterId)throws Exception
	{
		try {
			
			Optional<User> user = userRepository.findById(Long.parseLong(recruiterId));
			if(user.isPresent())
			{
				return savedProfileRepo.findByRecruiterUser(user.get());
			}
			else
			{
				throw new GenericException("Didn't find any user by this Id");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			throw new GenericException(e.getMessage());
		}
	}
	
	
	public void saveProfile(String userId,String recruiterId)throws Exception
	{
		try {
		Optional<User> user = userRepository.findById(Long.parseLong(userId));
		if(user.isPresent())
		{
			Optional<User> recruiter = userRepository.findById(Long.parseLong(recruiterId));
			if(!recruiter.isPresent())
			{
				throw new GenericException("Recuiter Id not found");
			}
			SavedProfile profile = new SavedProfile();
			profile.setApplicant(user.get());
			profile.setRecruiterUser(recruiter.get());
			profile.setApplicationDate(new Date());
			savedProfileRepo.save(profile);
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
