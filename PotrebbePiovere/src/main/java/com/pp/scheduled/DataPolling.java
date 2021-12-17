package com.pp.scheduled;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pp.interfaces.InterfacePolling;
import com.pp.model.CurrentWeather;
import com.pp.model.owm.OwmCurrentJson;
import com.pp.utils.Config;

/**
 * Gestione delle attività schedulate di salvataggio dati con spring
 * @author Andrea Giampieri
 *
 */
@Component
public class DataPolling implements InterfacePolling{
	
	
	@Scheduled(fixedRate = 10000) //inserire da config
	@Async
	public void getCurrentWeather() {
		RestTemplate restTemplate = new RestTemplate(); //oggetto mapper
		ArrayList<Long> cities = Config.getCities(); //lista delle citta da interrogare
		for(Long city: cities) {
			//APICall apicall = new APICall(city.toString());
			CurrentWeather cw = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id="+city.longValue()+"&appid="+Config.getConf("owm_apikey")+"&units=metric&lang=it", CurrentWeather.class);
			//this.cw = restTemplate.getForObject(apicall.getLink(),CurrentWeather.class);
			System.out.println(cw); //output per diagnostica
			cw.appendToFile(); //salvo il file, inserito binding dinamico dimostrativo
		}
	}
}
