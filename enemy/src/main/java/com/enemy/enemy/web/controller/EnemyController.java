package com.enemy.enemy.web.controller;

import com.enemy.enemy.dao.EnemyDao;
import com.enemy.enemy.exception.ResourceNotFoundException;
import com.enemy.enemy.model.Enemy;
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
public class EnemyController {

    private final EnemyDao enemyDao;

    public EnemyController(EnemyDao enemyDao) {
        this.enemyDao = enemyDao;
    }

    @Operation(summary = "Récupère une liste d'ennemis")
    @GetMapping("/EnemyAll")
    public List<Enemy> allEnemies() {
        return enemyDao.findAll();
    }

    @Operation(summary = "Récupère les infos d'un ennemi selon son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ennemi trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Enemy.class))}),
            @ApiResponse(responseCode = "400", description = "Ennemi introuvable",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ennemi non accessible",
                    content = @Content)})
    @GetMapping("/Enemy/{id}")
    public Optional<Enemy> displayEnemy(@Validated @PathVariable int id) {
        return enemyDao.findById(id);
    }

    @Operation(summary = "Ajoute un ennemi")
    @PostMapping("/EnemySave")
    public Enemy addEnemy(@Validated @RequestBody Enemy heroes) {
        return enemyDao.save(heroes);
    }

    @Operation(summary = "Modifie un ennemi selon son id")
    @PutMapping("/EnemyModify/{id}")
    public ResponseEntity<Enemy> modifyEnemy(@Validated @PathVariable int id, @RequestBody Enemy enemy) {
        Enemy updateEnemy = enemyDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le héros n'existe pas sous cet ID" + id));
        updateEnemy.setName(enemy.getName());
        updateEnemy.setType(enemy.getType());
        updateEnemy.setLife(enemy.getLife());
        enemyDao.save(updateEnemy);

        return ResponseEntity.ok(updateEnemy);
    }

    @Operation(summary = "Supprime un ennemi selon son id")
    @DeleteMapping("/EnemyDelete/{id}")
    public void deleteEnemy(@Validated @PathVariable int id) {
        enemyDao.deleteById(id);
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
