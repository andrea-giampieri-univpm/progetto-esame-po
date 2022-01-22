package com.data;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.WeatherException;
import com.model.City;
import com.service.WeatherService;

/**
 *Classe che si occupa di gestire le principali operazioni del database
 */
@Service
public class DatabaseManagment {
	
	JSONObject json;
	
	@Autowired
	CityDataRepository cityRepo;
	
	@Autowired
	WeatherDataRepository weatherRepo;
	
	@Autowired
	WeatherService service;
	
	/**
	 *metodo che restituisce una lista completa di tutti i dati presenti nel database
	 */
	public List<CityData> getAllData() {
		return cityRepo.findAll();
	}
	
	/**
	 *metodo che restituisce una lista completa di tutte le città presenti nel database
	 */
	public List<City> getAllCity() {
		List<City> cityListDB = new ArrayList<City>();
		for (CityData elem:getAllData()) {
			City city = new City(elem.getId(), elem.getName(), elem.getCountry());
			cityListDB.add(city);					
		}
		return cityListDB;
	}
	
	
	/**
	 *metodo che restituisce tutti i dati meteo presenti nel database per una specifica città
	 *@param int cityId 
	 */
	public List<WeatherData> getAllWeatherData(int cityId) {
		return cityRepo.findById(cityId).get().getWeather();
	}
	
	/**
	 *metodo che restituisce i singoli dati meteo per una specifica città
	 *@param int cityId, int id
	 */
	public WeatherData getWeatherData(int cityId, int id) {
		return getAllWeatherData(cityId).get(id);
	}
	
	/**
	 *metodo che restituisce i dati salvati nel database relativi una specifica città
	 *@param int cityId
	 */
	public CityData getCityData(int cityId) {
		return cityRepo.findById(cityId).get();
	}
	
	/**
	 *metodo che restituisce i dati salvati nel database relativi una specifica città
	 *@param String cityName
	 */
	public CityData getCityData(String cityName) {
		return cityRepo.findByName(cityName);
	}
	
	/**
	 *metodo che verifica la presenza di una città all'interno del database
	 *@return boolean
	 *@param int cityId
	 */
	public boolean isCityPresent(int cityId) {
		return cityRepo.findById(cityId).isPresent();
	}
	
	/**
	 *metodo che verifica la presenza di una città all'interno del database
	 *@return boolean
	 *@param string cityName
	 */
	public boolean isCityPresent(String cityName) {
		return cityRepo.existsByName(cityName);
	}
	
	/**
	 *metodo che verifica la presenza di dati meteo per una specifica città
	 *@return boolean
	 *@param int cityId
	 */
	public boolean isDataPresent(int cityId) {
		return !cityRepo.findById(cityId).get().getWeather().isEmpty();
	}
	
	/**
	 *metodo che aggiunge una città tramite ID all'interno del database
	 *@param int id
	 */
	public void addCity(int id) {
		try {
			json =service.getWeatherByCityId(id);
		}catch(Exception e) {
			new WeatherException(e.getMessage());
		}
		CityData city = new CityData(json.getInt("id"), 
									 json.getString("name"), 
							         json.getJSONObject("sys").getString("country"));
		cityRepo.save(city);
	}
	
	/**
	 *metodo che rimuove una città tramite ID dal database
	 *@param int id
	 */
	public void deleteCity(int id) {
		cityRepo.delete(cityRepo.findById(id).get());
	}
	
	/**
	 *metodo che elimina tutti i dati dal database
	 */
	public void deleteAllData() {
		cityRepo.deleteAll();
	}
	
	/**
	 *metodo che effettua il salvataggio dei dati meteo all'interno del database
	 */
	public void saveWeatherData(WeatherData weatherData) {
		weatherRepo.save(weatherData);
	}
	
	
	
}
