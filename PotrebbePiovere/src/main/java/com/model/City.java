package com.model;

import org.springframework.stereotype.Component;

/**
 *Modello base di una città
 */
@Component
public class City {
	
	int id;	
	String name, country;
	
	
	public City() {
		
	}
	
	public City(int id, String name, String country) {
		this.id = id;
		this.name = name;
		this.country = country;		
	}
	
	//Setter methods

	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
		
		
	//Getter methods
	
	public String getName() {
		return name;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getCountry() {
		return country;
	}


}