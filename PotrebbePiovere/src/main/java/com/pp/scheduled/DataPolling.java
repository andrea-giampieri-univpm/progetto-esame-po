package com.pp.scheduled;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pp.model.CurrentWeather;
import com.pp.utils.Config;

/**
 * Gestione delle attività schedulate di salvataggio dati con spring
 * @author Andrea Giampieri
 *
 */
@Component
public class DataPolling {
	
	
	@Scheduled(fixedRate = 10000) //inserire da config
	@Async
	public void getCurrentWeather() {
		RestTemplate restTemplate = new RestTemplate(); //oggetto mapper
		ArrayList<Long> cities = Config.getCities(); //lista delle citta da interrogare
		for(Long city: cities) {
			CurrentWeather cw = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id="+city.longValue()+"&appid="+Config.getConf("owm_apikey")+"&units=metric&lang=it", CurrentWeather.class);
			System.out.println(cw); //output per diagnostica
			cw.appendToFile(); //salvo il file
		}
	}
}
