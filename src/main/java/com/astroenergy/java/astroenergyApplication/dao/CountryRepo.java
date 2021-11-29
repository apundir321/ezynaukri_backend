package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroenergy.java.astroenergyApplication.model.Country;



public interface CountryRepo extends JpaRepository<Country,Integer>{

}
