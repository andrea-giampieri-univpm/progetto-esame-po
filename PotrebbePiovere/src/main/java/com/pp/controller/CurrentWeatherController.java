package com.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pp.model.CurrentWeather;

/**
 * classe di prova che restituisce il meteo istantaneo
 * @author Andrea Giampieri
 *
 */
@RestController
public class CurrentWeatherController {

	@GetMapping("/getinstant")
	public String getInstant(@RequestParam(value = "cityid") String cityId) {
		CurrentWeather cw = new CurrentWeather();
		System.out.println(cw); //logging
		return cw.toJsonString();
	}
}

