package com.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pp.model.CurrentWeather;
import com.pp.utils.Config;

/**
 * classe di prova che restituisce il meteo istantaneo
 * @author Andrea Giampieri
 *
 */
@RestController
public class CurrentWeatherController {

	@GetMapping("/getinstant")
	public String getInstant(@RequestParam(value = "cityid") long cityId) {
		RestTemplate restTemplate = new RestTemplate(); //oggetto mapper, valutare implementazione su classe currentweather
<<<<<<< HEAD
		String jsonstring=""; //stringa ritorno
		try {
			try {
				CurrentWeather cw = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id="+cityId+"&appid="+Config.getConf("owm_apikey")+"&units=metric&lang=it", CurrentWeather.class);
				jsonstring = cw.toJsonString();
			}
			catch(Exception e)
			{
				throw new CurrentWeatherException();
			}
			
		} catch (CurrentWeatherException e) {
			jsonstring=e.toString();
		} finally {
			System.out.println("fine chiamata a getInstant"); //a scopo dimostrativo per finally
		}
		return jsonstring;
=======
		CurrentWeather cw = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id="+cityId+"&appid="+Config.getConf("owm_apikey")+"&units=metric&lang=it", CurrentWeather.class);
		System.out.println(cw); //logging
		return cw.toJsonString();
>>>>>>> parent of abeca66 (aggiunto controllo eccezioni trycatch getinstant)
	}
}

