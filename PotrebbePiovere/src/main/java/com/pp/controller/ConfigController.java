package com.pp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pp.utils.Config;

/**
 * Si occupa di rispondere alle chiamate
 * che andranno a modificare la configurazione
 * tramite restcontroller
 * Vedere istruzioni su github
 * @author Andrea Giampieri
 *
 */
@RestController
public class ConfigController {
	
	@GetMapping(value="/addmonitoring",produces = "application/json;")
	public String addCity(@RequestParam(value = "cityid") String cityId) {
		try {
			Config.addCity(Long.parseLong(cityId));
			return  Config.toJsonString();
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(400, "Numero non valido",e);
		}
	}
	
	@GetMapping("/addmonitoringday")
	public String addCityDaily(@RequestParam(value = "cityid") String cityId, @RequestParam(value = "day") String day) {
		return "da implementare";
	}
	
	@GetMapping("/addmonitoringrange")
	public String addCityRange(@RequestParam(value = "cityid") String cityId,@RequestParam(value = "from") String from,@RequestParam(value = "to") String to) {
		return "da implementare";
	}
	
	@GetMapping(value = "/status",produces = "application/json;")
	public String getStatus() {
		return Config.toJsonString();
	}
	
	@GetMapping(value="/removemonitoring",produces = "application/json;")
	public String df(@RequestParam(value = "cityid") String cityId)  {
		try {
			Config.removeCity(Long.parseLong(cityId));
			return  Config.toJsonString();
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "numero non valido", e);
		}
	}

}
