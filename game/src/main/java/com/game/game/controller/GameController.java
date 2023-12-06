package com.game.game.controller;

import com.game.game.dao.GameDao;
import com.game.game.model.Board;
import com.game.game.model.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GameController {
    private final String apiHero = "172.22.114.79:8081/";
    private final String apiEnemy = "172.22.114.78:8082/";
    private final String apiEquipment = "172.22.114.58:8083/";
    private final String apiPotions = "172.22.114.78:8084/";
    private final String apiBoard = "172.22.114.78:8085/";
    private final String apiMenu = "172.22.114.79:8086/";
    private final String apiGame = "172.22.114.58:8087/";
    private final GameDao gameDao;
    private final RestTemplate restTemplate;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
        this.restTemplate = new RestTemplate();
    }

//    public void playGame() {
//        moveHero();
//        iCase caseActuelle = getiCase();
//        displayMenuIG();
//        switch (menu.UIstartGame()) { // Utilise la méthode UIstartGame() de la classe Menu.
//            case 1 -> caseActuelle.interaction(personnage);
//            case 2 -> {
//                plateau.reculerJoueur();
//                caseActuelle.interaction(personnage);
//            }
//            default -> menu.UIError();
//        }
//    }

    /**
     * Lancement du jeu :
     * Menu
     * 1. Créer perso
     * 2. ..
     * 3...
     *
     *
     * StartGame
     * MenuIG
     * 1. Moveplayer -> interaction
     * 2. reculerPlay -> interaction
     * 3 quitter la partie
     *
     */

    @GetMapping("menu")
    public void displayMenu() {
        restTemplate.getForObject(apiMenu + "menu", String.class);
    }

    @GetMapping("menu/ig")
    public void displayMenuIG() {
        restTemplate.getForObject(apiMenu + "menu", String.class);
    }

    @GetMapping("board")
    public void displayBoard() {
        restTemplate.getForObject(apiBoard + "display-board", String.class);
    }

    @GetMapping("hero-by-id")
    public Hero displayHeroById() {
        return (Hero) restTemplate.getForObject(apiHero + "hero/{id}", Object.class);
    }

    @GetMapping("init-board")
    public Board initializeBoard() {
        restTemplate.postForObject(apiBoard + "add-new-board", null, Void.class);
        return null;
    }

    @GetMapping("create-hero")
    public Hero createHero() {
        restTemplate.postForObject(apiHero + "herosave", null, Void.class);
        return null;
    }

    @GetMapping("modify-hero")
    public void modifyCharacter() {
        restTemplate.put(apiHero + "heromodify/{id}", null);
    }

    @GetMapping("move-hero")
    public void moveHero() {
        restTemplate.getForObject(apiBoard + "move-hero", String.class);
    }

    @GetMapping("save-hero")
    public void storeHeroInDatabase() {
        restTemplate.postForObject(apiHero + "herosave", null, Void.class);
    }

    @GetMapping("save-board")
    public void storeBoardInDatabase() {
        restTemplate.postForObject(apiBoard + "board/storeInDatabase", null, Void.class);
    }

    @GetMapping("display-all-hero")
    public void displayAllHero() {
        restTemplate.getForObject(apiHero + "heroall", String.class);
    }

    @GetMapping("display-all-game")
    public void displayAllGames() {
        restTemplate.getForObject(apiBoard + "displayAll", String.class);
    }

    @GetMapping("load-game")
    public void loadGame(int gameId) {
        restTemplate.getForObject(apiBoard + "load" + gameId, Void.class);
    }
}