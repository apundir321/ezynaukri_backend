package com.easynaukri.java.easynaukriApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.easynaukri.java.easynaukriApplication.dao.JobRepository;
import com.easynaukri.java.easynaukriApplication.dao.OrganizationRepository;
import com.easynaukri.java.easynaukriApplication.dao.RoleRepository;
import com.easynaukri.java.easynaukriApplication.dao.UserProfileRepository;
import com.easynaukri.java.easynaukriApplication.dao.UserRepository;
import com.easynaukri.java.easynaukriApplication.model.Job;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Location;
import com.easynaukri.java.easynaukriApplication.model.Organization;
import com.easynaukri.java.easynaukriApplication.model.Role;
import com.easynaukri.java.easynaukriApplication.model.Skills;
import com.easynaukri.java.easynaukriApplication.model.User;
import com.easynaukri.java.easynaukriApplication.model.UserProfile;
import com.easynaukri.java.easynaukriApplication.validation.Name;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class EasynaukriApplication {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	Name name;
	
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EasynaukriApplication.class, args);
	}
	
//	@PostConstruct
	public void saveRoles()
	{
		Role role = new Role();
		role.setName("ROLE_EMPLOYEE");

		Role role1 = new Role();
		
		role1.setName("ROLE_RECRUITER");
		
		roleRepository.save(role);
		roleRepository.save(role1);

	}
	
	@PostConstruct
	public void saveJob() throws JsonProcessingException
	{
		
		for(int i =0;i<15;i++) {
		User user = userRepository.findByEmail("anuragpundir621@gmail.com");
		
		Organization organization = new Organization();
		organization.setCode("GGN01");
		organization.setName("Flybunch");
		organization.setDescription("Digital marketing company");
		
		JobCategory category = new JobCategory();
		category.setJobCategoryCode("DESIGN-001");
		category.setJobCategoryName("UX Desginer");
		category.setDescription("Ux designer job");
		
		Job job = new Job();
		job.setCategory(category);
		job.setJob_code("DESIGN-001");
		job.setJobDescription("Need a designer with 3 years of experience");
		job.setJobName("WEB_DESIGNER");
		job.setJobPublished(new Date());
		job.setNoOfvacancies(4);
		job.setOrganization(organization);
		job.setPostedBy(user);
		job.setStartDate(new Date());
		job.setJobLocation("Gurgaon");
		job.setMinExperience(2);
		job.setMaxExperience(5);
		job.setMinSalary(3);
		job.setMaxSalary(10);
		
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(job));
		
		
		UserProfile profile = new UserProfile();
		profile.setAboutMe("HIII");
		profile.setCurrentCtc("5");
		profile.setExpectedCtc("10");
		profile.setCurrentJobRole("Software developer");
		profile.setMaxQualification("B.tech");
		profile.setWorkExperience("5");
		
		Location location = new Location();
		location.setLocation("Gurgaon");
		
		Location location2 = new Location();
		location2.setLocation("Bangalore");
		
		profile.getPrefferedLocation().add(location);
		profile.getPrefferedLocation().add(location2);
		
//		location.getProfiles().add(profile);
//		location2.getProfiles().add(profile);
//		
		Skills skill = new Skills();
		skill.setName("java");
		
		Skills skill1 = new Skills();
		skill1.setName("Database");
		
		profile.getTags().add(skill);
		profile.getTags().add(skill1);
		
//		skill.getProfiles().add(profile);
//		skill1.getProfiles().add(profile);
//		userRepository.deleteAll();
//		userProfileRepository.deleteAll();
//		userProfileRepository.save(profile);
		
//		List<UserProfile> profileData = (List<UserProfile>) userProfileRepository.findAll();
//		
//		System.out.println(mapper.writeValueAsString(profileData));
		
		
		
		
//		jobRepository.save(job);
		}
		
	}
	
	 @Bean
	 public WebMvcConfigurer corsConfigurer() 
	    {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*")
					.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
	            }
	        };
	    }

}
