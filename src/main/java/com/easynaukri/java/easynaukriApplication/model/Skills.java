package com.easynaukri.java.easynaukriApplication.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Entity
@Table(name = "skills")
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
//	@Column(unique = true)
	private String name;
//	@ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//            },
//            mappedBy = "tags")
//    private Set<UserProfile> profiles = new HashSet<>();
	public Skills() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	@JsonIgnore
//	public Set<UserProfile> getProfiles() {
//		return profiles;
//	}
//	public void setProfiles(Set<UserProfile> profiles) {
//		this.profiles = profiles;
//	}
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.readValue("{\"id\":0,\"name\":\"Anurag\",\"strings\":[\"java\",\"sql\"]}", HashMap.class));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
