package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.security.Principal;
import java.util.List;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;

public interface TrainerService {

	public List<Trainer> getAllTrainers(Principal principal);
	public Trainer getTrainer(String name);
}
