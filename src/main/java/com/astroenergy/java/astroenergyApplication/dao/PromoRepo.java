package com.astroenergy.java.astroenergyApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroenergy.java.astroenergyApplication.model.PromoCode;



public interface PromoRepo extends JpaRepository<PromoCode,Integer> {

}
