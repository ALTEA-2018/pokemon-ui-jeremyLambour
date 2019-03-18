package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private String PokemonTypeServiceUrl;

    public RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod="getDefaultListPokemon")
    public List<PokemonType> listPokemonsTypes() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
    	HttpEntity<String> entity =  new HttpEntity<>(headers);
        PokemonType[] pokemonTypes =  restTemplate.exchange(getPokemonTypeServiceUrl()+"/pokemon-types/",HttpMethod.GET,entity,PokemonType[].class).getBody();
        return Arrays.asList(pokemonTypes);
    }
    
    @Cacheable("pokemon-types")
    public PokemonType getPokemonById(Integer id) {
    
        PokemonType pokemonType =  restTemplate.getForObject(getPokemonTypeServiceUrl()+"/pokemon-types/{id}",PokemonType.class,id);
        return pokemonType;
    }
    
    public List<PokemonType> getPokemonDefaultList(){
		return new ArrayList<>();
    	
    }

    @Autowired
    @Qualifier("restTemplate")
	public void setRestTemplate(RestTemplate restTemplate) {
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