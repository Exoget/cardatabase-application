package com.packt.cardatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ownerid;
	private String firstName, lastName;
	
	//si on supprime le owner tous les cars qui sont liés vont etre supprimés
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	//Ignore to produce JSON output of a property
	//@JsonIgnore
	private List<Car> cars;
	
	public Owner() {
		super();
	}


	public Owner(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public long getOwnerid() {
		return ownerid;
	}


	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public List<Car> getCars() {
		return cars;
	}


	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
}
