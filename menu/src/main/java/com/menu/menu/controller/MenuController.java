package com.menu.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MenuController {

    @GetMapping("/menu")
    void displayMenu(Model model) {
        model.addAttribute("title", "Donjons & Dragons");
    }

    @GetMapping("/start")
    public String start() {
        return "start";
    }

    @GetMapping("/create-hero")
    public String createHero(Model model) {
        model.addAttribute("create-hero", "Créer votre héros");
        return "createHero";
    }

    @GetMapping("/display-heroes")
    public String displayHeroes(Model model) {
        model.addAttribute("display-hero", "Votre liste de héros");
        return "displayHeroes";
    }

    @GetMapping("/created-heroes")
    public String chooseHero(Model model) {
        model.addAttribute("choose-hero", "Veuillez choisir un héro");
        return "chooseHero";
    }


    @GetMapping("/quit")
    public String quit() {
        return "redirect:/menu"; // Redirige vers le menu après le démarrage du jeu
    }

}