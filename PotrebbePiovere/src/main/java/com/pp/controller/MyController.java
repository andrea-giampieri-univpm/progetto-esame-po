package com.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/hello")
	public String result(@RequestParam("city") String city, Model model) {
		model.addAttribute("city", city);
		model.addAttribute("country", "IT");
		return "weather";
	}
	
	
	
}