package com.distrib.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distrib.demo.model.CountryRepository;

@Controller
public class CountryController {
	@Autowired 
	CountryRepository countryRepository;
	
	@RequestMapping("/country")
	public String country(Model model) {
		
		Iterable countryList = countryRepository.findAll();
		model.addAttribute("countryList",countryList);
		String test="tteeeeessssstttt";
		model.addAttribute("test",test);
		return "country";
	}

}
