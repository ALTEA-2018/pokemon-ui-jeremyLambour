package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.security.Principal;
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
public class TrainerServiceImpl implements TrainerService{

	private RestTemplate template;

	private String trainerApiUrl;
	
	@Autowired
	private PokemonTypeService pokemonService;

	@Override
	public List<Trainer> getAllTrainers(Principal principal) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		Trainer[] trainers = template
				.exchange(getTrainerApiUrl() + "/trainers/", HttpMethod.GET, entity, Trainer[].class)
				.getBody();
		List<Trainer> trainersList = Arrays.asList(trainers);
		List<Trainer> tempsTrainers = trainersList.parallelStream().filter(trainer -> !trainer.getName().equalsIgnoreCase(principal.getName())).collect(Collectors.toList());
		for(Trainer trainer : tempsTrainers) {
			trainer.setTrainerTeam(trainer.getTeam().parallelStream().map(x -> pokemonService.getPokemonById(x.getPokemonType())).collect(Collectors.toList()));
		}		
		return tempsTrainers;
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

	
	
	public Trainer getTrainer(String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		Trainer trainer = template
				.getForObject(getTrainerApiUrl() + "/trainers/{name}",Trainer.class,name);
		return trainer;
	}

}
