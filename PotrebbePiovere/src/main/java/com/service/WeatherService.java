package com.service;

import org.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *Classe che si occupa di effettuare chiamate alla API OpenWeatherMap
 */
@Service
public class WeatherService{
	
	WebClient weatherWebClient;
	String lang = "it", units = "metric";
	String apiKey;
	
	//Costruzione URL base OpenWeatherMap
	public WeatherService() {
	    weatherWebClient = WebClient.create("http://api.openweathermap.org/data/2.5/weather");
	}
	
	
	/**
	 *Metodo che effettua la chiamata alla API OpenWeatherMap tramite nome di città
	 *@return  JSONObject
	 *@param String cityName
	 */
	public JSONObject getWeatherByCityName(String cityName) {	
		try {
			return new JSONObject(weatherWebClient
				.get()
	            .uri(uriBuilder -> uriBuilder
	                                .queryParam("q", cityName)
	                                .queryParam("appid", apiKey)
	                                .queryParam("units", units)
	                                .queryParam("lang", lang)
	                                .build())
	            .retrieve()  
	            .bodyToMono(String.class)
	            .block());
		}
		catch(WebClientResponseException ex) {
			System.out.println("APICall_Exception: " + ex.getStatusCode() + " : " + ex.getMessage());
			throw ex;
		}
		catch(Exception e) {
			System.out.println("APICall_exception: " + e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 *Metodo che effettua la chiamata alla API OpenWeatherMap tramite ID di città
	 *@return  JSONObject
	 *@param int cityId
	 */
	public JSONObject getWeatherByCityId(int cityId) {	
		try {
			 return new JSONObject(weatherWebClient
				.get()
	            .uri(uriBuilder -> uriBuilder
	                                .queryParam("id", cityId)
	                                .queryParam("appid", apiKey)
	                                .queryParam("units", units)
	                                .queryParam("lang", lang)
	                                .build())
	            .retrieve()  
	            .bodyToMono(String.class)
	            .block());
		}
		catch(WebClientResponseException ex) {
			System.out.println("APICall_Exception: Error " + ex.getStatusCode() + " : " +   ex.getStatusText());
			throw ex;
		}
		catch(Exception e) {
			System.out.println("ApiCall_Exception..." + e.getMessage());
			throw e;
		}
	}
			

	//Setter methods
	
	public void setLang(String lang) {
		this.lang = lang;
	}


	public void setUnits(String units) {
		this.units = units;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	
	//Getter methods
	

	public String getLang() {
		return lang;
	}


	public String getUnits() {
		return units;
	}




	
}