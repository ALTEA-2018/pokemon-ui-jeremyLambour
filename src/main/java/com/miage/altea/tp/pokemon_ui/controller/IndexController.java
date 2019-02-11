package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping(value="/")
     String index(){
        return "index";
    }

    @PostMapping(value="/registerTrainer")
    public ModelAndView registerNewTrainer(@RequestBody String name){
        ModelAndView view = new ModelAndView();
        view.setViewName("register");
        view.addObject("name",name);
        return view;
    }
}
