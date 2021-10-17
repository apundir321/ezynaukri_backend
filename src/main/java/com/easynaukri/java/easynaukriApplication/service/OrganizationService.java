package com.easynaukri.java.easynaukriApplication.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easynaukri.java.easynaukriApplication.dao.JobCategoryRepo;
import com.easynaukri.java.easynaukriApplication.dao.JobRepoCustom;
import com.easynaukri.java.easynaukriApplication.dao.JobRepository;
import com.easynaukri.java.easynaukriApplication.dao.OrganizationRepository;
import com.easynaukri.java.easynaukriApplication.error.GenericException;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.Organization;

@Service
public class OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	JobRepository jobRepoCustom;
	
	@Autowired
	JobCategoryRepo jobCategoryRepo;
	
	public Organization addOrganization(Organization organization,String categoryId)throws Exception
	{
		Organization o= null; 
		try {
		Optional<JobCategory> category = jobCategoryRepo.findById(Integer.parseInt(categoryId));
		if(category.isPresent())
		{
			organization.setCategory(category.get());
		}
		Organization org = organizationRepository.findByName(organization.getName());
		if(org!=null)
		{
			throw new GenericException("Organization already Exists");
		}
		o = organizationRepository.save(organization);
		}catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
		return o;
	}
	
	public List<Organization> getOrganizations()throws Exception
	{
		try {
			return organizationRepository.findAll();
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
	
	public Optional<Organization> getOrgById(Long id)
	{
		return organizationRepository.findById(id);
	}
	
	public List<Organization> getOrganizationsByCriteria(Map<String, Object> searchCriteria)throws Exception
	{
		try {
			return jobRepoCustom.findOrgsByCriteria(searchCriteria);
		} catch (Exception e) {
			throw new GenericException(e.getMessage());
		}
	}
}

