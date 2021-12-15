package com.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//di esempio, da implementare, controller per le statistiche
@RestController
public class StatsController {

	@GetMapping("/getstats")
	public String getStats(@RequestParam(value = "cityid") String cityId) {
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/getstatsfiltered")
	public String getStatsFiltered(@RequestParam(value = "cityid") String cityId,@RequestParam(value = "from") String from,@RequestParam(value = "to") String to) {
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/getstatsfilteredarr") //statistica con array di id da fare in post
	public String getStatsFilterfd(@RequestParam(value = "cityid") String cityId,@RequestParam(value = "from") String from,@RequestParam(value = "to") String to) {
		return "Greetings from Spring Boot!";
	}
}
