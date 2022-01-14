package com;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.model.Weather;
import com.service.WeatherService;
import com.utils.CityList;

@SpringBootApplication
public class TodaySForecastApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TodaySForecastApplication.class, args);
		new CityList();
		Weather weather = new Weather("pescara");
		System.out.println(weather.getName());
		System.out.println(weather.getId());
		System.out.println(weather.getTemp());
		WeatherService service = new WeatherService();
		System.out.println(service.getWeatherByCityName("pescara"));
	}

}
