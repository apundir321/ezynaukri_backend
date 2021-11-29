package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroenergy.java.astroenergyApplication.model.Ratelist;



public interface RatelistRepo extends JpaRepository<Ratelist,Integer> {

}
