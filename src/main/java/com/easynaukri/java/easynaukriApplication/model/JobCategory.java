package com.easynaukri.java.easynaukriApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobCategory {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	private String jobCategoryCode;
	private String jobCategoryName;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobCategoryCode() {
		return jobCategoryCode;
	}
	public void setJobCategoryCode(String jobCategoryCode) {
		this.jobCategoryCode = jobCategoryCode;
	}
	public String getJobCategoryName() {
		return jobCategoryName;
	}
	public void setJobCategoryName(String jobCategoryName) {
		this.jobCategoryName = jobCategoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
