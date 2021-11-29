package com.astroenergy.java.astroenergyApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroenergy.java.astroenergyApplication.model.PromoCode;
import com.astroenergy.java.astroenergyApplication.service.PromoService;



@RestController
public class PromoController {
@Autowired
PromoService promoService;

@RequestMapping("/addPromo")
public ResponseEntity<?> addPromo(@RequestBody PromoCode promo) {
	try {
      PromoCode p = promoService.addPromo(promo);
	 return new ResponseEntity<PromoCode>(p, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping("/editPromo")
public ResponseEntity<?> editPromo(@RequestBody PromoCode promo) {
	try {
      PromoCode p = promoService.editPromo(promo);
	 return new ResponseEntity<PromoCode>(p, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping("/deletePromo")
public ResponseEntity<?> deletePromo(@RequestBody PromoCode promo) {
	try {
      PromoCode p = promoService.deletePromo(promo);
	 return new ResponseEntity<PromoCode>(p, HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


}
