package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;

import java.util.List;


public class Trainer {

	private String name;


	private List<Pokemon> team;

	private List<PokemonType> trainerTeam;

	private String password;

	public Trainer() {
	}
	
	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Trainer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<Pokemon> getTeam() {
		return team;
	}



	public void setTeam(List<Pokemon> team) {
		this.team = team;
	}



	public List<PokemonType> getTrainerTeam() {
		return trainerTeam;
	}



	public void setTrainerTeam(List<PokemonType> trainerTeam) {
		this.trainerTeam = trainerTeam;
	}

	
	
}
