package com.easynaukri.java.easynaukriApplication.model;

import java.util.List;

public class FilterJob {
	
	private String organization;
	private String category;
	private String minExperience;
	private String maxExperience;
	private String minSalary;
	private String maxSalary;
	private String jobPublished;
	private List<String> tags;
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMinExperience() {
		return minExperience;
	}
	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}
	public FilterJob() {
		super();
	}
	public String getMaxExperience() {
		return maxExperience;
	}
	public void setMaxExperience(String maxExperience) {
		this.maxExperience = maxExperience;
	}
	public String getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}
	public String getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getJobPublished() {
		return jobPublished;
	}
	public void setJobPublished(String jobPublished) {
		this.jobPublished = jobPublished;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
