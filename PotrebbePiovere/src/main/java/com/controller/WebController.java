package com.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.data.DatabaseManagment;
import com.data.WeatherData;
import com.exception.WeatherException;
import com.model.Weather;
import com.service.WeatherService;
import com.utils.CityList;
import com.utils.Statistics;
import com.view.Validation;

/**
 *Controller che garantisce un semplice utilizzo dell'applicazione da parte dell'utente
 *tramite pagine Web
 */
@Controller
public class WebController {

	@Autowired
	Weather weather;
	Statistics statistic;
	
	@Autowired
	WeatherService service;
	
	
	@Autowired
	DatabaseManagment database;
	
	/**
	 *Acquisizione dati forniti in input tramite HomePage
	 */
	@GetMapping("/home")
	public String homePage(@ModelAttribute Validation form, Model model) {
		
		model.addAttribute("search", form);
		model.addAttribute("cityForm", form);
		return "index";
	}

	/**
	 *Elaborazione dei dati in input ed eventale reindirizzamento alla pagina "weather" 
	 */
	@PostMapping("/weather")
	public String weatherPage(@ModelAttribute Validation form, BindingResult result, Model model) {
		if (!result.hasGlobalErrors()) {
			//Elaborazione dati forniti in input e relativo controllo
			boolean exist = false;
			if (!form.getNameCheckbox().isEmpty()) {
				exist = searchByName(form.getNameInputModal(), form.getUnits(), form.getLang());
			}
			else {
				if(!form.getIdCheckbox().isEmpty())
					exist = searchById(form.getIdInputModal(), form.getUnits(), form.getLang());
				else{
					if (!form.getNameInputModal().isEmpty() || form.getIdInputModal()!=0 )
						System.out.println("Please select one checkbox...");
					else {
						form.setUnits(service.getUnits());
						form.setLang(service.getLang());
						exist = searchById(form.getIdInput(), form.getUnits(), form.getLang());
					}	
				}
			}
			if(exist) {
				//Trasferimento dati elaborati alla WeatherPage
				setUnitsView(form.getUnits(), model);
				weatherDataTransfer(model, weather);
				return "weather";
			}	
		}
		return "redirect:/home";	
	}
	
	
	/**
	 *In presenza di dati meteo su cui effettuare statistiche si viene reindirizzati alla 
	 *pagina "weather" contenente questa volta anche le relative statistiche
	 */
	@PostMapping("/statistics")
	public String statisticPage(Model model) {
			List<Integer> data = new ArrayList<Integer>();
			for (WeatherData elem:database.getAllWeatherData(weather.getId())) {
				data.add(elem.getPressure());
			}		
			setUnitsView(service.getUnits(), model);
			weatherDataTransfer(model, weather);
			statistic = new Statistics(data);
			statisticsDataTransfer(statistic, model);
			return "weather";	
	}
	
	/**
	 *metodo che verifica l'esistenza di un ID di città e lo usa per effettuare una chiamata all'API OpenWeatherMap
	 */
	private boolean searchById(int id, String units, String lang) {
		boolean found = false;
		if (CityList.isPresent(id)) {
			try {
				weather.setWeather(id, units, lang);
				found = true;
			} catch(Exception e) {
				new WeatherException(e.getMessage());
			}
		}
		else 
			System.out.println("City's ID " +  id + " does not exist or it is not valid" );
		return found;
	}
	
	
	/**
	 *metodo che verifica l'esistenza di un nome di città e lo usa per effettuare una chiamata all'API OpenWeatherMap
	 */
	private boolean searchByName(String name, String units, String lang) {
		boolean found = false;
		if (CityList.isPresent(name)) {
			try {
				weather.setWeather(name, units, lang);
				found = true;
				} catch(Exception e) {
					new WeatherException(e.getMessage());
				}
		}
		else 
			System.out.println("City's name " + name + " does not exist or it is not valid");
		return found;
	}

	/**
	 *metodo che passa i parametri meteo alla pagina web
	 */
	public void weatherDataTransfer(Model model, Weather weather) {
		model.addAttribute("name", weather.getName());
		model.addAttribute("id", weather.getId());
		model.addAttribute("country", weather.getCountry());
		model.addAttribute("temp", weather.getTemp());
		model.addAttribute("pressure", weather.getPressure());
		model.addAttribute("humidity", weather.getHumidity());
		model.addAttribute("cloudy", weather.getCloudy());
		model.addAttribute("wind", weather.getWind());
		model.addAttribute("rain", weather.getRain());
		model.addAttribute("snow", weather.getSnow());
		model.addAttribute("description", weather.getDescription());
		model.addAttribute("weatherIcon", "http://openweathermap.org/img/w/" + weather.getIcon() + ".png");
		model.addAttribute("buttonType", "button");
		if (database.isCityPresent(weather.getId())) {
			if (database.isDataPresent(weather.getId())) {
				setStatisticsRange(model);				 
				model.addAttribute("buttonType", "submit");
			}	
			else {
				model.addAttribute("error", "Statistic not available for this city...Weather data not found");
			}
		}
		else 
			model.addAttribute("error", "Statistic not available for this city...City data not found");

	}
	
	/**
	 *metodo che imposta il range disponibile sul quale effettuare statistiche
	 */
	private void setStatisticsRange(Model model) {
		//Prendo la data relativa al primo dato meteo dal database
		String firstDate = database.getWeatherData(weather.getId(), 0).getTime();
		//Prendo la data relativa l'ultimo dato meteo dal database
		String lastDate = database.getWeatherData(weather.getId(), database.getAllWeatherData(weather.getId()).size()-1).getTime();
		model.addAttribute("firstDateRange", firstDate);
		model.addAttribute("lastDateRange", lastDate);
		//converto la data in modo tale da essere accettabile come attributo per l'input datetime html
		firstDate = firstDate.substring(0,10) + "T" + firstDate.substring(11);
		lastDate = lastDate.substring(0,10) + "T" + lastDate.substring(11);
		model.addAttribute("firstDate", firstDate);
		model.addAttribute("lastDate", lastDate);	
	}

	
	/**
	 *metodo che passa i dati relativi alle statistiche meteo alla pagina web
	 */
	public void statisticsDataTransfer(Statistics statistics, Model model) {
		model.addAttribute("min", statistic.getMin());
		model.addAttribute("max", statistic.getMax());
		model.addAttribute("average", statistic.getAverage());
		model.addAttribute("variance", statistic.getVariance());
	}
	
	
	public void setUnitsView(String units, Model model) {
		switch (units) {
		case "metric": 
			model.addAttribute("simbol", "C");break;
		case "standard":
			model.addAttribute("simbol", "K");break;
		case "imperial":
			model.addAttribute("simbol", "F");break;
		}
	}
	
	
	
	
}