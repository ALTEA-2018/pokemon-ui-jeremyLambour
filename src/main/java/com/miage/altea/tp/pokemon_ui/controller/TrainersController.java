package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerService;

@Controller
@RequestMapping("/trainers")
public class TrainersController {

	@Autowired
	private TrainerService service;
	
	  @GetMapping("/")
	    public ModelAndView trainers(){
	        ModelAndView view = new ModelAndView();
	        view.setViewName("trainer");
	        view.addObject("trainers",service.getAllTrainers());
	        return  view;
	    }
}
