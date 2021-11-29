package com.astroenergy.java.astroenergyApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int sno;
private String name;
private String code;
private String status;
private String remarks;

public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
@Override
public String toString() {
	return "Country [sno=" + sno + ", name=" + name + ", code=" + code + ", status=" + status + ", remarks=" + remarks
			+ "]";
}
}
