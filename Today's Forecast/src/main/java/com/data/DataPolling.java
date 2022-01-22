package com.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.service.WeatherService;

/**
 *Classe che si occupa del salvataggio periodico dei dati meteo per ogni città
 *presente nel database
 *@see DatabaseManagment
 */
@Component
public class DataPolling {
	
	WeatherData weatherData;
	JSONObject json;
	
	@Autowired
	WeatherService service;
	
	@Autowired
	DatabaseManagment database;
	
	@Scheduled(fixedRate = 3600000 )
	@Async
	public void DataStorage() {
		for (CityData elem:database.getAllData()) {
			json = service.getWeatherByCityName(elem.getName());			
			weatherData = new WeatherData(elem, json.getJSONObject("main").getDouble("temp"),
											json.getJSONObject("main").getInt("pressure"),
											getCurrentTime());
			database.saveWeatherData(weatherData);
		}
	}
	
	private String getCurrentTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String strDate = dateFormat.format(date);
		return strDate;
	}
}
