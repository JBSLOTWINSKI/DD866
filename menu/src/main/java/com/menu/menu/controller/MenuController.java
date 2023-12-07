package com.menu.menu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menu.menu.model.FillHero;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MenuController {

    private RestTemplate restTemplate;

    MenuController() {
        restTemplate = new RestTemplate();
    }

    @GetMapping("/menu")
    void displayMenu(Model model) {
        model.addAttribute("title", "Donjons & Dragons");
    }

    /**
     * Création d'un héros
     * @param model
     * @return
     */

//    @PostMapping("/create-hero")
//    public String createHero(Model model) {
//        model.addAttribute("create-hero", "Créer votre héros");
//        ResponseEntity<> response = restTemplate.postForObject("http://172.22.114.58:8081/menu/herosave", Hero.class);
//
//        model.addAttribute("herosave", response);
//        return "herosave";
//   }

    /**
     * Affichage de la liste héros
     *
     * @param model
     * @return
     */

    @GetMapping("/display-heroes")
    public String displayHeroes(Model model) {
        model.addAttribute("display-hero", "Votre liste de héros");
        List response = restTemplate.getForObject("http://172.22.114.58:8081/heroall", List.class);

        model.addAttribute("display-heroes", response);
        return "display-heroes";
    }

    /**
     * Jouer une partie
     *
     * @param model
     * @return
     */

//    @GetMapping("/start")
//    public String start(Model model) {
//        model.addAttribute("start-game", "Voici votre héros :");
//        Board response = restTemplate.getForObject("http://172.22.114.58:8081/hero", Board.class);
//
//        model.addAttribute("hero");
//        return "start";
//    }


    /**
     * Quitter la page
     *
     * @return
     */
    @GetMapping("/quit")
    public String quit() {
        return "redirect:/menu"; // Redirige vers le menu après le démarrage du jeu
    }

}