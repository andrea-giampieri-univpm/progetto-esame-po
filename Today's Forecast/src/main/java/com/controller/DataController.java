package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.City;
import com.model.Weather;
import com.data.CityData;
import com.data.DatabaseManagment;
import com.exception.WeatherException;
import com.utils.CityList;


/**
 *REST Controller che ci permette di ottenere il meteo corrente e 
 *di compiere le principali azione di gestione dei dati meteo salvati all'interno del database
 */
@RequestMapping("/data")
@RestController
public class DataController {
	
	Object obj;
	CityData city;
	
	@Autowired
	DatabaseManagment database;
	
	@Autowired
	Weather weather;
	
	
	/**
	 *metodo che mostra il meteo corrente di una città tramite ID
	 *@return Weather
	 */
	@GetMapping(value="/currentWeatherById")
	public Weather currentWeatherById(@RequestParam int id) throws WeatherException {
		if (CityList.isPresent(id)) {
				weather.setWeather(id);
				return weather;
		}
		else 
			throw new WeatherException("City_ID " + id + " not found or invalid."); 
		}
		
	
	/**
	 *metodo che mostra il meteo corrente di una città tramite nome
	 *@return Weather
	 */
	@GetMapping("/currentWeatherByName")
	public Weather currentWeatherByName(@RequestParam String name) throws WeatherException {
		if (CityList.isPresent(name)) {
				weather.setWeather(name);
				return weather; 
			}
		else
			throw new WeatherException("City_name " + name + " not found or invalid.");			
		
		}

	/**
	 *metodo che mostra tutti i dati presenti nel database
	 *@return List<CityData>
	 */
	@GetMapping("/all")
	public List<CityData> getAllData() {
		return database.getAllData();
		}
	
	/**
	 *metodo che mostra tutte le città presenti nel database
	 *@return List<City>
	 */
	@GetMapping("/allCities")
	public List<City> getAllCitiesData() {
		return database.getAllCity();
		}
	
	/**
	 *metodo che mostra tutti i dati meteo relativi a una città presente nel database
	 *@param int id
	 *@return CityData
	 */
	@GetMapping("/weatherDataByCityId")
	public CityData getWeatherById(@RequestParam(required = true) int id) throws WeatherException {
		if (database.isCityPresent(id))
			return database.getCityData(id);
		else 	
			throw new WeatherException("City_ID " + id + " not found or invalid");
		}
	
	/**
	 *metodo che mostra tutti i dati meteo relativi a una città presente nel database
	 *@param String name
	 *@return CityData
	 */
	@GetMapping("/weatherDataByCityName")
	public CityData getWeatherByName(@RequestParam(required = true) String name) throws WeatherException {
		if (database.isCityPresent(name))
				return database.getCityData(name);
		else
			throw new WeatherException("City_Name " + name + " not found or invalid.");
		}
	
	/**
	 *metodo che aggiunge una città nel database
	 *@return String
	 */
	@GetMapping("/add")
	public String add(@RequestParam(required = true) int id) {
		String str;
		if (CityList.isPresent(id)) {
			if (!database.isCityPresent(id)) {
					database.addCity(id);
					str = "The city is added"; 
			}
			else 
				str = "City_ID" + id + " already exist.";
		}	
		else
			str = "City_ID " + id + "not found or invalid.";
		return str;
	}
				
	/**
	 *metodo che rimuove una città dal database
	 *@return String
	 */
	@DeleteMapping("/delete")
	public String delete(@RequestParam(required = true) int id) {
		String str;
		if (database.isCityPresent(id)) {
			database.deleteCity(id);
			str = "City is delete";
		}
		else 
			str = "City_ID " + id + "not found or invalid.";
		return str;
	}
	
	/**
	 *metodo che elimina tutti i dati presenti nel database
	 *@return String
	 */
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		database.deleteAllData();	
		return "All data are delete";
	}
}
