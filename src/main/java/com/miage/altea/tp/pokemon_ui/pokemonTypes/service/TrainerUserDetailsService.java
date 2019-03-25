package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.TrainerDetails;

@Service
public class TrainerUserDetailsService implements UserDetailsService {

	@Autowired
	TrainerService trainerService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
		Trainer find = trainerService.getTrainer(username);
		if(find != null) {
			TrainerDetails details = new TrainerDetails();
			details.setTrainer(find);
			return details;
		}else {
			throw new BadCredentialsException("No such user");
		}
	}
	
	
	public TrainerService getTrainerService() {
		return trainerService;
	}
	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
	
	
	

}
