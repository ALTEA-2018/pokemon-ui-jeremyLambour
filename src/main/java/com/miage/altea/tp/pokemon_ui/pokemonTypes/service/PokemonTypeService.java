package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

import java.util.List;

import org.springframework.web.client.RestTemplate;

public interface PokemonTypeService {
    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonById(Integer id);
    public void setRestTemplate(RestTemplate template);

}

