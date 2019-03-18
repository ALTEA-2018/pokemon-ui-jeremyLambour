package com.miage.altea.tp.pokemon_ui.controller;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    public PokemonTypeService pokemonTypeService;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        ModelAndView view = new ModelAndView();
        view.setViewName("pokedex");
        view.addObject("pokemonTypes",pokemonTypeService.listPokemonsTypes());
        return  view;
    }
    

    public PokemonTypeService getService() {
        return pokemonTypeService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService service) {
        this.pokemonTypeService = service;
    }
}
