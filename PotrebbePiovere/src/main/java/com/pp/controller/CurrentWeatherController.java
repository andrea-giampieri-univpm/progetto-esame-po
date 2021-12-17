package com.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pp.exceptions.CurrentWeatherException;
import com.pp.model.CurrentWeather;

/**
 * classe di prova che restituisce il meteo istantaneo
 * in caso di errore restituisce json di errore
 * @author Andrea Giampieri
 */
@RestController
public class CurrentWeatherController {

	@GetMapping("/getinstant")
	public String getInstant(@RequestParam(value = "cityid") long cityId) {
		String jsonstring; //stringa ritorno
		try {
			CurrentWeather cw = new CurrentWeather(cityId);
			jsonstring = cw.toJsonString();
		} catch (CurrentWeatherException e) {
			jsonstring=e.toString();
		} finally {
			System.out.println("ricevuta chiamata api getinstant (demo del blocco finally)");
		}
		return jsonstring;
	}
}

