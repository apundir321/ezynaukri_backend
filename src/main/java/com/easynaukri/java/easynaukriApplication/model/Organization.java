package com.easynaukri.java.easynaukriApplication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Organization {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private JobCategory category;	

	private String code;
	private String name;
	private String description;
	private String orgProfileImage;
	private String location;
	
	 @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	private Set<Job> jobs;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgProfileImage() {
		return orgProfileImage;
	}

	public void setOrgProfileImage(String orgProfileImage) {
		this.orgProfileImage = orgProfileImage;
	}

	public JobCategory getCategory() {
		return category;
	}

	public void setCategory(JobCategory category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
