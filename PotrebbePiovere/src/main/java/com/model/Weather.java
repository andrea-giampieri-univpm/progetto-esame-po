package com.model;

import org.json.JSONObject;
import com.service.WeatherService;

public class Weather extends City {
	double temp, wind, rain, snow;
	int pressure, humidity, cloudy;
	String description, icon;
	
	JSONObject json;
	WeatherService service = new WeatherService();
	
	
	public Weather(String name) {
		json = new JSONObject(service.getWeatherByCityName(name));
		super.setId(json.getInt("id"));
		super.setName(name);
		setWeatherParam();
	}
	
	public Weather(int id) {
		json = new JSONObject(service.getWeatherByCityId(id));
		super.setName(json.getString("name"));
		super.setId(id);
		setWeatherParam();
	}
	
	public void setWeatherParam() {
		temp = json.getJSONObject("main").getDouble("temp");
		wind = json.getJSONObject("wind").getDouble("speed");
		pressure = json.getJSONObject("main").getInt("pressure");
		humidity = json.getJSONObject("main").getInt("humidity");
		cloudy = json.getJSONObject("clouds").getInt("all");
		description = json.getJSONArray("weather").getJSONObject(0).getString("description");
		icon = json.getJSONArray("weather").getJSONObject(0).getString("icon");
		super.country = json.getJSONObject("sys").getString("country");
		if (json.has("rain"))
			rain = json.getJSONObject("rain").getDouble("1h");
		else
			rain = 0;
		if (json.has("snow"))
			snow = json.getJSONObject("snow").getDouble("1h");
		else
			snow = 0;
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
