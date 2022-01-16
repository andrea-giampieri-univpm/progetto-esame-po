package com;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.exception.ConfigException;
import com.model.Weather;
import com.service.WeatherService;
import com.utils.CityList;
import com.utils.Config;

@SpringBootApplication
@EnableScheduling
public class TodaySForecastApplication {

	public static void main(String[] args) throws IOException {
		//AG: inizializzo configurazione, se non c'è viene terminata l'app
				try {
					Config.initialize();
				} catch (ConfigException e) {
					System.out.println(e);
					System.exit(1);
				}
				
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
