package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private String PokemonTypeServiceUrl;

    public RestTemplate restTemplate;

    public List<PokemonType> listPokemonsTypes() {
        PokemonType[] pokemonTypes =  restTemplate.getForObject(getPokemonTypeServiceUrl()+"/pokemon-types/",PokemonType[].class);
        return Arrays.asList(pokemonTypes);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.PokemonTypeServiceUrl = pokemonServiceUrl;
    }

    public String getPokemonTypeServiceUrl() {
        return PokemonTypeServiceUrl;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }




}