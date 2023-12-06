package com.game.game.controller;

import com.game.game.model.Board;
import com.game.game.model.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    private final String apiHero = "http://172.22.114.58:8081/";
    private final String apiEnemy = "http://172.22.114.58:8082/";
    private final String apiEquipment = "http://172.22.114.58:8083/";
    private final String apiPotions = "http://172.22.114.58:8084/";
    private final String apiBoard = "http://172.22.114.58:8085/";
    private final String apiMenu = "http://172.22.114.58:8086/";
    private final String apiGame = "http://172.22.114.58:8087/";
    private final RestTemplate restTemplate;
    protected boolean gameIsOver = false;

    public GameController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("play-game")
    public ResponseEntity<String> playGame() {
        Hero hero = loadHeroById(1);
        Board board = initializeBoard();

        displayMenuIG();

        moveHero();

        storeHeroInDatabase();
        storeBoardInDatabase();

        return ResponseEntity.ok().body("ok");
    }

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

    public Hero loadHeroById(int id) {
        return restTemplate.getForObject(apiHero + "hero/" + id, Hero.class);
    }

    @GetMapping("board")
    public void displayBoard() {
        restTemplate.getForObject(apiBoard + "display-board", String.class);
    }

    @GetMapping("hero-by-id/{id}")
    public String displayHeroById(@PathVariable int id) {
        return restTemplate.getForObject(apiHero + "hero/" + id, String.class);
    }


    @PostMapping("create-hero")
    public Hero createHero(@PathVariable Hero hero) {
        return restTemplate.postForObject(apiHero + "herosave", hero, Hero.class);
    }

    @PutMapping("modify-hero/{id}")
    public void modifyCharacter(@PathVariable int id, @RequestBody Hero hero) {
        restTemplate.put(apiHero + "heromodify/" + id, hero);
    }

    @GetMapping("load-game")
    public ResponseEntity<Void> loadGame(@RequestParam int gameId) {
        restTemplate.getForObject(apiBoard + "load/" + gameId, Void.class);
        return ResponseEntity.ok().build();
    }

    @GetMapping("init-board")
    public Board initializeBoard() {
        restTemplate.postForObject(apiBoard + "board", null, Void.class);
        return null;
    }

    @GetMapping("move-hero")
    public ResponseEntity<ArrayList<String>> moveHero() {
        restTemplate.getForObject(apiBoard + "move-hero", String.class);

        ArrayList<String> data = new ArrayList<>();
        data.add("case = 1");
        data.add("hasEnemy = non");
        return ResponseEntity.ok().body(data);
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
    public String displayAllHero() {
        return restTemplate.getForObject(apiHero + "heroall", String.class);
//        return restTemplate.getForObject("http://172.22.114.58:8081/heroall", String.class);
    }

    @GetMapping("display-all-game")
    public void displayAllGames() {
        restTemplate.getForObject(apiBoard + "displayAll", String.class);
    }
}