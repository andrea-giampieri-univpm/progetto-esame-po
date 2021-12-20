package com.pp.scheduled;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pp.exceptions.CurrentWeatherException;
import com.pp.model.CurrentWeather;
import com.pp.utils.Config;

/**
 * Gestione delle attività schedulate di salvataggio dati con spring
 * @author Andrea Giampieri
 *
 */
@Component
public class DataPolling {
	
	@Scheduled(fixedRate = 3600000) //inserire da config?
	@Async
	public void getCurrentWeather() {
		ArrayList<Long> cities = Config.getCities(); //lista delle citta da interrogare
		for(Long city: cities) {
			try {
				CurrentWeather cw = new CurrentWeather(city);
				System.out.println(cw); //output per diagnostica
				cw.appendToFile(); //salvo il file
			} catch (CurrentWeatherException e) {
				System.out.println(e);
			}
		}
	}
}
