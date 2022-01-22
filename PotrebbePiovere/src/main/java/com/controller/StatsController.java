package com.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exception.CurrentWeatherException;
import com.model.AGCity;
import com.model.CurrentWeather;
import com.utils.Config;
import com.utils.Statistics;

/**
 * Controller per le statistiche, vedere istruzioni su github
 * @author Andrea Giampieri
 *
 */
@RestController
public class StatsController {

	@GetMapping(value="/getstats",produces = "application/json;")
	public String getStats(@RequestParam(value = "cityid") long cityId) {
		try {
			AGCity city = new AGCity(cityId);
			return city.getStatJson();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
		}
		
	}
	
	@GetMapping(value="/getstatsfiltered",produces = "application/json;")
	public String getStatsFiltered(@RequestParam(value = "cityid") long cityId,@RequestParam(value = "from") long from,@RequestParam(value = "to") long to) {
		try {
			AGCity city = new AGCity(cityId,from,to);
			return city.getStatJson();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
		}
	}
	
	@PostMapping("/getstatsfilteredmulti") //statistica con array di date da fare in post
	public String getStatsFilterfd(@RequestBody String body) {
		return "Greetings from Spring Boot!";
	}
	
	//con implementazione statistiche di francesco
	@GetMapping(value="/getstatsbis",produces = "application/json;")
	public String getStatsBis(@RequestParam(value = "cityid") long cityId) {
		ArrayList<Integer> pressureValue=new ArrayList<Integer>();
		try {
			try {
				FileReader fr = new FileReader(Config.getConf("data_path")+"data_"+cityId+".json");
				BufferedReader br = new BufferedReader(fr);
				String line;
				do {//leggo il file dei dati riga per riga, ogni riga è un oggetto currentweather
					line=br.readLine();
					try {//gestisco separatamente una riga errata nel file
						CurrentWeather cw = new CurrentWeather(line);
						pressureValue.add((int)cw.getPressure());
					} catch (Exception e) {
						System.out.println("riga ignorata in parsing dati storici:\n"+line);
					}
				} while(line!=null);
				br.close();
	        } catch (FileNotFoundException e) {
	        	throw new CurrentWeatherException("Dati storici citta "+cityId+" non esistente");
	        }catch (Exception e) {
	        	throw new CurrentWeatherException("errore non specificato storico citta");
	        }
			Statistics stat= new Statistics(pressureValue);
			JSONObject json = new JSONObject();
			json.put("id", cityId);
			json.put("min", stat.getMin());
			json.put("max", stat.getMax());
			json.put("media", stat.getAverage());
			json.put("varianza", stat.getVariance());
			return json.toString();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
		}
		
	}
}
