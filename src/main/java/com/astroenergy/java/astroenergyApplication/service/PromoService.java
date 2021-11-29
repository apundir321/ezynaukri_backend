package com.astroenergy.java.astroenergyApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroenergy.java.astroenergyApplication.dao.PromoRepo;
import com.astroenergy.java.astroenergyApplication.model.PromoCode;



@Service
public class PromoService {
	@Autowired
	PromoRepo promoRepo;
	public PromoCode addPromo(PromoCode promo)throws Exception
	{
		try {
			PromoCode p = promoRepo.save(promo);
			 return p;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}

	public PromoCode editPromo(PromoCode promo)throws Exception
	{
		try {
			promo.setStatus("DELETED");
			PromoCode p = promoRepo.save(promo);
			 return p;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}
	public PromoCode deletePromo(PromoCode promo)throws Exception
	{
		try {
			PromoCode p = promoRepo.save(promo);
			 return p;
			}catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	}
}
