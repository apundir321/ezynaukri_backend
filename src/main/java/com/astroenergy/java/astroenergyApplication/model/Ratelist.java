package com.astroenergy.java.astroenergyApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ratelist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sno;
	private String countryName;
	private String consultationType;
	private int rateOfValues;
	private String status;
	private String remarks;
	
	public Ratelist() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getConsultationType() {
		return consultationType;
	}
	public void setConsultationType(String consultationType) {
		this.consultationType = consultationType;
	}
	public int getRateOfValues() {
		return rateOfValues;
	}
	public void setRateOfValues(int rateOfValues) {
		this.rateOfValues = rateOfValues;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Ratelist [sno=" + sno + ", countryName=" + countryName + ", consultationType=" + consultationType
				+ ", rateOfValues=" + rateOfValues + ", status=" + status + ", remarks=" + remarks + "]";
	}
	
	
	
}
