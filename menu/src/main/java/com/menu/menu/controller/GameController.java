//package com.menu.menu.controller;
//
//import com.menu.menu.model.FillHero;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Controller
//public class GameController {
//
//    private RestTemplate restTemplate;
//
//    GameController() {
//        restTemplate = new RestTemplate();
//    }
//
//    @GetMapping("/menu")
//    String getGame(Model model) {
//        model.addAttribute("title", "Donjons & Dragons");
//        model.addAttribute("welcome", "Bienvenue à vous ! Prêt à jouer ?");
//
//        model.addAttribute("start", "Start");
//        model.addAttribute("create-hero", "Créer un joueur");
//
//        return "menu";
//    }
//
//    @GetMapping("/create-hero")
//    String createPlayer(Model model) {
//        FillHero heroForm = new FillHero();
//        model.addAttribute("create-hero-message", "Créer un joueur");
//        model.addAttribute("fillHero", heroForm);
//        return "createPlayer";
//    }
//
//    @GetMapping("/display-heroes")
//    String getHeroesList(Model model) {
//        model.addAttribute("display-heroes-message", "Liste des héros");
//        List response = restTemplate.getForObject("172.22.114.79:8086/", List.class);
//
//        model.addAttribute("display-heroes", response);
//        return "display-heroes";
//    }
//
//
//    @PostMapping("/menu")
//    public String putInDatabase(Model model, @ModelAttribute("fillForm") FillHero fillHero) {
//        model.addAttribute("created-hero-title", "Votre personnage est créé");
//        model.addAttribute("fillHero", fillHero);
//
//        FillHero response = restTemplate.postForObject("172.22.114.79:8086/", fillHero, FillHero.class);
//
//        return "created-hero";
//    }
//
//}
