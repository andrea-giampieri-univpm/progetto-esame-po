package com.service;

import org.springframework.web.reactive.function.client.WebClient;


public class WeatherService {
	
	WebClient weatherWebClient;
	String lang;
	String units;
	String key = "7deb142293a7b78618d16a8b358dede3";
	
	
	public WeatherService() {
	    weatherWebClient = WebClient.create("http://api.openweathermap.org/data/2.5/weather");
	}
	
	
	public String getWeatherByCityName(String cityName) {
		return weatherWebClient				
	    		.get()
	            .uri(uriBuilder -> uriBuilder
	                                .queryParam("q", cityName)
	                                .queryParam("units", "metric")
	                                .queryParam("appid", key)
	                                .build())
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();
	}
	
	
	public String getWeatherByCityId(int  cityId) {
		return weatherWebClient				
	    		.get()
	            .uri(uriBuilder -> uriBuilder
	                                .queryParam("id", cityId)
	                                .queryParam("units", "metric")
	                                .queryParam("appid", key)
	                                .build())
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();
	}
	
}