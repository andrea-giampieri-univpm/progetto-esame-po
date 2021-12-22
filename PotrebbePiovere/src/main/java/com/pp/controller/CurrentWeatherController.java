package com.pp.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pp.exceptions.CurrentWeatherException;
import com.pp.model.CurrentWeather;

/**
 * classe di prova che restituisce il meteo istantaneo
 * in caso di errore restituisce json di errore
 * @author Andrea Giampieri
 */
@RestController
public class CurrentWeatherController {

	@GetMapping(value="/getinstant",produces = "application/json;")
	public String getInstant(@RequestParam(value = "cityid") long cityId) {
		String jsonstring; //stringa ritorno
		try {
			CurrentWeather cw = new CurrentWeather(cityId); //creo nuvo
			jsonstring = cw.toJsonString();
		} catch (CurrentWeatherException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString(),e);
		} finally {
			System.out.println("ricevuta chiamata api getinstant");
		}
		return jsonstring;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="/getinstantarr",produces = "application/json;")
	public String getInstantPost(@RequestBody String cities) {
		JSONArray retArr = new JSONArray();
		try {
			JSONArray cityArr = (JSONArray) new JSONParser().parse(cities); //verifico l'array in ingresso
			for(Object city: cityArr) {
				CurrentWeather cw = new CurrentWeather(Long.parseLong(city.toString()));
				retArr.add(new JSONParser().parse(cw.toJsonString())); //per ogni città creo l'oggetto cw e lo aggiungo a quelli da ritornare
			}
			return retArr.toJSONString();
		} catch (CurrentWeatherException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString(),e);
		}  catch (ParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString(),e);
		} finally {
			System.out.println("ricevuta chiamata api getinstantarr");
		}
	}
	
}

