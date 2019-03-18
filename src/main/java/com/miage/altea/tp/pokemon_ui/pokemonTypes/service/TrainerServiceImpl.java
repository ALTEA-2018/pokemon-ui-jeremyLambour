package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService {

	private RestTemplate template;

	private String trainerApiUrl;
	
	@Autowired
	private PokemonTypeService pokemonService;

	@Override
	public List<Trainer> getAllTrainers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		Trainer[] trainers = template
				.exchange(getTrainerApiUrl() + "/trainers/", HttpMethod.GET, entity, Trainer[].class)
				.getBody();
		List<Trainer> trainersList = Arrays.asList(trainers);
		for(Trainer trainer : trainersList) {
			trainer.setTrainerTeam(trainer.getTeam().stream().map(x -> pokemonService.getPokemonById(x.getPokemonType())).collect(Collectors.toList()));
		}		
		return trainersList;
	}

	public RestTemplate getTemplate() {
		return template;
	}

	@Autowired
	@Qualifier("trainerApiRestTemplate")
	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public String getTrainerApiUrl() {
		return trainerApiUrl;
	}

	@Value("${trainer.service.url}")
	public void setTrainerApiUrl(String trainerApiUrl) {
		this.trainerApiUrl = trainerApiUrl;
	}

}
