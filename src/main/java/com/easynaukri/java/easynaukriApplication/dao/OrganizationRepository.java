package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easynaukri.java.easynaukriApplication.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	public Organization findByName(String orgName);

}
