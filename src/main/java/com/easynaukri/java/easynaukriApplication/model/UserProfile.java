package com.easynaukri.java.easynaukriApplication.model;

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
import javax.persistence.OneToMany;

@Entity
public class UserProfile {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
    private String lastName;
    private String email;
	private String aboutMe;
	private String maxQualification;
	private String workExperience;
	private String currentCtc;
	private String expectedCtc;
	private String currentJobRole;
	private String currentOrganization;
	private String profilePicName;
	private String currentLocation;
	private String resume;
	private String phoneNo;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "profile_skills", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = {
			@JoinColumn(name = "skill_id") })
	private Set<Skills> tags = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "profile_location", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = {
			@JoinColumn(name = "location_id") })
	private Set<Location> prefferedLocation = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private Set<Education> educations = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private Set<WorkExperience> workExperiences = new HashSet<>();

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getMaxQualification() {
		return maxQualification;
	}

	public void setMaxQualification(String maxQualification) {
		this.maxQualification = maxQualification;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}

	public String getExpectedCtc() {
		return expectedCtc;
	}

	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	public String getCurrentJobRole() {
		return currentJobRole;
	}

	public void setCurrentJobRole(String currentJobRole) {
		this.currentJobRole = currentJobRole;
	}

	public Set<Skills> getTags() {
		return tags;
	}

	public void setTags(Set<Skills> tags) {
		this.tags = tags;
	}

	public Set<Location> getPrefferedLocation() {
		return prefferedLocation;
	}

	public void setPrefferedLocation(Set<Location> prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", aboutMe=" + aboutMe + ", maxQualification=" + maxQualification
				+ ", workExperience=" + workExperience + ", currentCtc=" + currentCtc + ", expectedCtc=" + expectedCtc
				+ ", currentJobRole=" + currentJobRole + ", tags=" + tags + ", prefferedLocation=" + prefferedLocation
				+ "]";
	}

	public Set<Education> getEducations() {
		return educations;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	public Set<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(Set<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(String currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
