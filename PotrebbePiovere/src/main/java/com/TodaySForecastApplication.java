package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.exception.ConfigException;
import com.model.Weather;
import com.service.WeatherService;
import com.utils.Config;

@SpringBootApplication
@EnableScheduling
public class TodaySForecastApplication {	

	@Autowired
	Config config;
	
	public static void main(String[] args) {
		SpringApplication.run(TodaySForecastApplication.class, args);
	}	
	

}
