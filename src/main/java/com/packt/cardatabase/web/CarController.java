package com.packt.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@RestController
public class CarController {

	@Autowired
	private CarRepository carRep;

	@Autowired
	private OwnerRepository ownerRep;
	
	/*
	 * @RequestMapping("/cars") public Iterable<Car> getCars() {
	 * 
	 * return carRep.findAll(); }
	 */
	
//	@RequestMapping("/owners")
//	public Iterable<Owner> getOwners() {
//		
//		return ownerRep.findAll();
//	}
}
