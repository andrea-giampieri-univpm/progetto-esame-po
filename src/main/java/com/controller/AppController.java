package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.Weather;
import com.utils.CityList;
import com.view.Validation;

@Controller
public class AppController {
	
	Weather weather;


	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public String homePage(@ModelAttribute Validation form, Model model) {		
		model.addAttribute("search", form);
		model.addAttribute("cityForm", form);
		return "index";
	}
	
	@RequestMapping(value="/weather", method=RequestMethod.POST)
	public String weatherPage(@Validated @ModelAttribute Validation form, BindingResult result, Model model) {
		if (result.hasGlobalErrors()==false) {
			boolean exist = false;
			if (!form.getNameCheckbox().isEmpty()) {
				if (CityList.listName.contains(form.getNameInputModal())) {
					weather = new Weather(form.getNameInputModal());
					exist = true;
				}
				else
					System.out.println("City's name does not exist" + form.getNameInputModal());
			}
			
			if(!form.getIdCheckbox().isEmpty()){
				if (CityList.listId.contains(form.getIdInputModal())) {
					weather = new Weather(form.getIdInputModal());
					exist = true;
				}
				else 
					System.out.println("City's ID does not exist");
			}
			
			if(form.getNameCheckbox().isEmpty() && form.getIdCheckbox().isEmpty()){
				if (CityList.listId.contains(form.getIdInput())) {
					weather = new Weather(form.getIdInput());
					exist = true;
				}
				else 
					System.out.println("City's ID does not exist");
			}
			
			if(exist==true) {
				model.addAttribute("cityName", weather.getName());
				model.addAttribute("cityId", weather.getId());
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
				return "weather";
			}	
		}
		return "redirect:/";	
		
	}


/*	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/hello")
	public String result(@RequestParam("city") String city, Model model) {
		model.addAttribute("city", city);
		model.addAttribute("country", "IT");
		return "weather";
	}*/
	
}