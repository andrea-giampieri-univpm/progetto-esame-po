package com.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *Classe che rappresenta una tabella relativa ai dati meteo salvati nel database
 *In Relazione ManyToOne con CityData
 *@see CityData
 */
@Entity(name = "Weather")
public class WeatherData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	double temp;
	int pressure;
	String time;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id", nullable = false)
    CityData  city;
	
	//Costruttore vuoto necessario ad Hibernate
	public WeatherData() {
	}
	
	public WeatherData(CityData city, double temp, int pressure, String time) {
		this.city = city;
		this.temp = temp;
		this.pressure = pressure;
		this.time = time;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCity(CityData city) {
		this.city = city;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getId() {
		return id;
	}

	public CityData getCity() {
		return city;
	}

	public double getTemp() {
		return temp;
	}
	
	public int getPressure() {
		return pressure;
	}

	public String getTime() {
		return time;
	}


}

