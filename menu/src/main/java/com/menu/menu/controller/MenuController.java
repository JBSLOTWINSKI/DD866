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
//        return "menu";
    }

    @GetMapping("/create-hero")
    public String createHero(Model model) {
        model.addAttribute("createhero", "Créer votre héros");
        return "createHero";
    }

    @GetMapping("/display-heroes")
    public String displayHeroes(Model model) {
        model.addAttribute("displayhero", "Votre liste de héros");
        return "displayHeroes";
    }

    @GetMapping("/choose-your-hero")
    public String chooseHero(Model model) {
        model.addAttribute("choosehero", "Veuillez choisir un héro");
        return "chooseHero";
    }

    @GetMapping("/play")
    public String play() {
        return "play";
    }

    @GetMapping("/quit")
    public String quit() {
        return "redirect:/menu"; // Redirige vers le menu après le démarrage du jeu
    }




//    private int affichageMenu() {
//        // Affiche-moi ---->
//        System.out.println("Que souhaitez-vous faire ?");
//        System.out.println("[1] Créer un personnage");
//        System.out.println("[2] Modifier le personnage");
//        System.out.println("[3] JOUER !");
//        System.out.println("[4] Quitter le jeu");
//        int playerChoice = scanner.nextInt();
//        return playerChoice;
//    }

//    public void gameMenu() {
//        // Je crée un booléan de vérification que je détermine comme faux
//        boolean isReady = false;
//        Game game = new Game();
//
//        // Tant que isReady est faux
//        while (!isReady) {
//            switch (affichageMenu()) {
//                case 1 -> {
//                    hero = newHero();
//                    System.out.println(hero);
//                }
//                case 2 -> {
//                    if (hero != null) hero = modifyHero();
//                }
//                case 3 -> {
//                    if (hero != null) game.move(hero);
//                }
//                case 4 -> {
//                    System.out.println("Oh nooon vous avez quitté le jeu ! A bientôt :) ");
//                    // isReady devient vrai ce qui permet de sortir de la boucle while
//                    isReady = true;
//                }
//                default -> System.out.println("Veuillez entrer un choix valide !");
//            }
//        }
//    }
//
//    public Hero newHero() {
//        Scanner newHero = new Scanner(System.in);
//        System.out.print("Taper le nom : ");
//        String nameHero = newHero.nextLine().toUpperCase();
//
//        System.out.print("Taper le Type : Magicien ou Guerrier ? ");
//        String typeHero = newHero.nextLine().toUpperCase();
//
//        if (typeHero.equalsIgnoreCase("Guerrier")) {
//            return hero = new Warrior(nameHero);
//        } else {
//            typeHero.equalsIgnoreCase("Magicien");
//            return hero = new Magician(nameHero);
//        }
//    }
//
//    private Hero modifyHero() {
//        Scanner newHero = new Scanner(System.in);
//        String nameHero = null;
//        String typeHero = null;
//
//        System.out.print("Voulez vous changer des infos?  [y/n]");
//        if (newHero.nextLine().equals("y")) {
//            System.out.print("Voulez vous changer le nom?  [y/n]");
//            if (newHero.nextLine().equals("y")) {
//                System.out.print("Taper le nouveau nom : ");
//                nameHero = newHero.nextLine();
//            }
//            System.out.print("Voulez vous changer la classe?  [y/n]");
//            if (newHero.nextLine().equals("y")) {
//                System.out.print("Taper la nouvelle classe : ");
//                typeHero = newHero.nextLine();
//            }
//        }
//        if (typeHero.equalsIgnoreCase("Guerrier")) {
//            return hero = new Warrior(nameHero);
//        } else {
//            typeHero.equalsIgnoreCase("Magicien");
//            return hero = new Magician(nameHero);
//        }
//    }

}
