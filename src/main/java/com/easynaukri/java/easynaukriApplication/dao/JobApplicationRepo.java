package com.easynaukri.java.easynaukriApplication.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easynaukri.java.easynaukriApplication.model.JobApplication;
import com.easynaukri.java.easynaukriApplication.model.JobCategory;
import com.easynaukri.java.easynaukriApplication.model.User;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication,Integer>{

	public Page<JobApplication> findAllByApplicant(User user,Pageable page);
}

