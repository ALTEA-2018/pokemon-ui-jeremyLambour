package com.miage.altea.tp.pokemon_ui.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class BattleController {

	@GetMapping("/fight/{opponent}")
	public ModelAndView fight(Principal principal, @PathVariable String opponent) {
		var modelAndView = new ModelAndView("fight");

		modelAndView.addObject("trainerName", principal.getName());
		modelAndView.addObject("opponentName", opponent);

		return modelAndView;
	}
}
