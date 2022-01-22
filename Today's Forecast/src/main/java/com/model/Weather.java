package com.model;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.service.WeatherService;

/**
 *Classe che rappresenta il meteo di una città
 *@extends City
 *@see City
 */
@Component
public class Weather extends City {
	
	double temp, wind, rain = 0, snow = 0;
	int pressure, humidity, cloudy;
	String description, icon;
	
	@Autowired
	WeatherService service;
	
	public void setWeather(JSONObject json) {
		setAllParam(json);
	}

	public void setWeather(String name) {
		setAllParam(service.getWeatherByCityName(name));
	}
	
	public void setWeather(int id) {
		setAllParam(service.getWeatherByCityId(id));
	}
	
	public void setWeather(String name, String units, String lang) {
		service.setLang(lang);
		service.setUnits(units);
		setAllParam(service.getWeatherByCityName(name));
	}
	
	public void setWeather(int id, String units, String lang) {
		service.setLang(lang);
		service.setUnits(units);
		setAllParam(service.getWeatherByCityId(id));
	}
	
	//Setter methods
	
	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setWind(double wind) {
		this.wind = wind;
	}

	public void setRain(double rain) {
		this.rain = rain;
	}

	public void setSnow(double snow) {
		this.snow = snow;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public void setCloudy(int cloudy) {
		this.cloudy = cloudy;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	//Settaggio di tutti i parametri meteo, utile per evitare codice ridondante
	public void setAllParam(JSONObject json) {
		setTemp(json.getJSONObject("main").getDouble("temp"));
		setWind(json.getJSONObject("wind").getDouble("speed"));
		setPressure(json.getJSONObject("main").getInt("pressure"));
	    setHumidity(json.getJSONObject("main").getInt("humidity"));
		setCloudy(json.getJSONObject("clouds").getInt("all"));
		setDescription(json.getJSONArray("weather").getJSONObject(0).getString("description"));
		setIcon(json.getJSONArray("weather").getJSONObject(0).getString("icon"));
		super.setName(json.getString("name"));
		super.setId(json.getInt("id"));
		super.setCountry(json.getJSONObject("sys").getString("country"));
		if (json.has("rain"))
			setRain(json.getJSONObject("rain").getDouble("1h"));
		if (json.has("snow"))
			setSnow(json.getJSONObject("snow").getDouble("1h"));
	}
	

	//Getter methods
	
	

	public double getTemp() {
		return temp;
	}
	
	public double getWind() {
		return wind;
	}
	
	public double getRain() {
		return rain;
	}
	
	public double getSnow() {
		return snow;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public int getHumidity() {
		return humidity;
	}
	
	public int getCloudy() {
		return cloudy;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getIcon() {
		return icon;
	}


}
