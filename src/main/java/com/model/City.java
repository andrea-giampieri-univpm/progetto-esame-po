package com.model;

public class City {
	
	int id;
	String name, country;
	
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