package com.hero.hero.web.controller;

import com.hero.hero.dao.HeroDao;
import com.hero.hero.exception.ResourceNotFoundException;
import com.hero.hero.model.Hero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class HeroController {

    private final HeroDao heroDao;

    public HeroController(HeroDao heroDao) {
        this.heroDao = heroDao;
    }


    @Operation(summary = "Récupère une liste de personnages")
    @GetMapping("/HeroAll")
    public List<Hero> allHeroes() {
        return heroDao.findAll();
    }

    @Operation(summary = "Récupère les infos d'un personnage selon son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personnage trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hero.class))}),
            @ApiResponse(responseCode = "400", description = "Personnage introuvable",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personnage non accessible",
                    content = @Content)})
    @GetMapping("/Hero/{id}")
    public Optional<Hero> displayHero(@Validated @PathVariable int id) {
        return heroDao.findById(id);
    }

    @Operation(summary = "Ajoute un personnage")
    @PostMapping("/HeroSave")
    public Hero addHero(@Validated @RequestBody Hero heroes) {
        return heroDao.save(heroes);
    }

    @Operation(summary = "Modifie un personnage selon son id")
    @PutMapping("/HeroModify/{id}")
    public ResponseEntity<Hero> modifyHero(@Validated @PathVariable int id, @RequestBody Hero hero) {
        Hero updateHero = heroDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le héros n'existe pas sous cet ID" + id));
        updateHero.setName(hero.getName());
        updateHero.setType(hero.getType());
        updateHero.setLife(hero.getLife());
        heroDao.save(updateHero);

        return ResponseEntity.ok(updateHero);
    }

    @Operation(summary = "Supprime un personnage selon son id")
    @DeleteMapping("/HeroDelete/{id}")
    public void supprimerUnPersonnage(@Validated @PathVariable int id) {
        heroDao.deleteById(id);
    }

    // La méthode ci-après permet de gérer les erreurs de création de champs dans la BDD

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
