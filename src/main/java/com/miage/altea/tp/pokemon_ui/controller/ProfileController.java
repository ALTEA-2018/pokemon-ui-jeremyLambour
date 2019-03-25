package com.miage.altea.tp.pokemon_ui.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private TrainerService service;
	
	@Autowired
	private PokemonTypeService pokemonService;

	@GetMapping("/")
	public ModelAndView trainers(Principal principal) {
		Trainer trainer = service.getTrainer(principal.getName());
		trainer.setTrainerTeam(trainer.getTeam().stream().map(x -> pokemonService.getPokemonById(x.getPokemonType())).collect(Collectors.toList()));
		ModelAndView view = new ModelAndView();
		view.setViewName("profile");
		
		view.addObject("profileTrainer", trainer);
		return view;
	}
}
