package com.miage.altea.tp.pokemon_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerService;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerUserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	TrainerService trainerService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public UserDetailsService userDetailsService() {
		TrainerUserDetailsService details = new TrainerUserDetailsService();
		details.setTrainerService(trainerService);
		return details;
	}
	
	@Autowired
	public void setTrainerService(TrainerService service) {
		this.trainerService = service;
	}
}
