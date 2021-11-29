package com.astroenergy.java.astroenergyApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroenergy.java.astroenergyApplication.dao.RatelistRepo;
import com.astroenergy.java.astroenergyApplication.model.Ratelist;



@Service
public class RateListService {
@Autowired
RatelistRepo rateListRepo;

public Ratelist addRatelist(Ratelist rateList)throws Exception
{
	try {
		Ratelist r = rateListRepo.save(rateList);
		 return r;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
}


public List<Ratelist> getAllRatelist()throws Exception
{
	try {
		return  rateListRepo.findAll();
		
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
}
public Ratelist editRatelist(Ratelist rateList)throws Exception
{
	try {
		Ratelist r = rateListRepo.save(rateList);
		 return r;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
}
public Ratelist deleteRatelist(Ratelist rateList)throws Exception
{
	try {rateList.setStatus("DELETED");
		Ratelist r = rateListRepo.save(rateList);
		 return r;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
}
}
