package com.data;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *Classe che rappresenta una tabella relativa alle città salvate nel database
 *In Relazione OneToMany con WeatherData
 *@see WeatherData
 */
@Entity(name = "city")
public class CityData {

	@Id
	int id;
	String name, country;	
	@OneToMany(mappedBy="city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<WeatherData> weatherData;
	
	//Costruttore vuoto necessario ad Hibernate
	public CityData() {
	}
	

	public CityData(int id, String name, String country) {
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

	public List<WeatherData> getWeather() {
		return weatherData;
	}


	public void setWeather(List<WeatherData> weatherData) {
		this.weatherData = weatherData;
	}


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

