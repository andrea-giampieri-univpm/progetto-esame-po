package com.pp.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value="/getinstant",produces = "application/json;")
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
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="/getinstantarr",produces = "application/json;")
	public String getInstantPost(@RequestBody String cities) {
		String jsonstring="";
		JSONArray retArr = new JSONArray();
		
		try {
			JSONArray cityArr = (JSONArray) new JSONParser().parse(cities);
			
			for(Object city: cityArr) {
				CurrentWeather cw = new CurrentWeather(Long.parseLong(city.toString()));
				retArr.add(new JSONParser().parse(cw.toJsonString()));
			}
			jsonstring=retArr.toString();
			jsonstring=retArr.toJSONString();
		} catch (CurrentWeatherException e) {
			jsonstring=e.toString();
		}  catch (ParseException e) {
			jsonstring=e.toString();
		} finally {
			System.out.println("ricevuta chiamata api getinstant (demo del blocco finally)");
		}
		
		return jsonstring;
	}
	
}

