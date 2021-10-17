package com.easynaukri.java.easynaukriApplication.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "job_post")
public class Job {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String job_code;
	private String jobName; 
	private String jobDescription;
	private Date startDate;
	private Date jobPublished;
	private int noOfvacancies;
	private int minExperience;
	private int maxExperience;
	private int minSalary;
	private int maxSalary;
	private String jobLocation;
	private String companyImageName;
	@DateTimeFormat(pattern = "dd=MM-yyyy")
	private Date endDate;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private JobCategory category;	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "Job_tags", joinColumns = { @JoinColumn(name = "job_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_id") })
	private Set<JobTags> tags = new HashSet<>();
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "posted_by")
	private User postedBy;
	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJob_code() {
		return job_code;
	}
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getJobPublished() {
		return jobPublished;
	}
	public void setJobPublished(Date jobPublished) {
		this.jobPublished = jobPublished;
	}
	public int getNoOfvacancies() {
		return noOfvacancies;
	}
	public void setNoOfvacancies(int noOfvacancies) {
		this.noOfvacancies = noOfvacancies;
	}
//	public JobCategory getCategory() {
//		return category;
//	}
//	public void setCategory(JobCategory category) {
//		this.category = category;
//	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@JsonIgnore
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	public int getMinExperience() {
		return minExperience;
	}
	public void setMinExperience(int minExperience) {
		this.minExperience = minExperience;
	}
	public int getMaxExperience() {
		return maxExperience;
	}
	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}
	public JobCategory getCategory() {
		return category;
	}
	public void setCategory(JobCategory category) {
		this.category = category;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public Set<JobTags> getTags() {
		return tags;
	}
	public void setTags(Set<JobTags> tags) {
		this.tags = tags;
	}
	public String getCompanyImageName() {
		return companyImageName;
	}
	public void setCompanyImageName(String companyImageName) {
		this.companyImageName = companyImageName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
