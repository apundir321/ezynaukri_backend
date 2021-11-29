package com.astroenergy.java.astroenergyApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroenergy.java.astroenergyApplication.dao.CountryRepo;
import com.astroenergy.java.astroenergyApplication.model.Country;



@Service
public class CountryServce {
	
	@Autowired
	CountryRepo countryRepo;
	
	
	public Country addCountry(Country country)throws Exception
	{
		try {
			Country c = countryRepo.save(country);
			 return c;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}
	
	public List<Country> getAll()throws Exception
	{
		try {
			
			 return countryRepo.findAll();
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}
	public Country updateCountry(Country country)throws Exception
	{
		try {
			Country c = countryRepo.save(country);
			 return c;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}
	public Country deleteCountry(Country country)throws Exception
	{
		try {
			country.setStatus("DELETED");
			Country c=countryRepo.save(country);
			 return c;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}

}
