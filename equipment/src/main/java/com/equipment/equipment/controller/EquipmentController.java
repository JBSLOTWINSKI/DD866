package com.equipment.equipment.controller;

import com.equipment.equipment.dao.EquipmentDao;
import com.equipment.equipment.exceptions.EquipmentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.equipment.equipment.model.Equipment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EquipmentController {
    private final EquipmentDao equipmentDao;

    public EquipmentController(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Operation(summary = "Obtenir tous les équipements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipements trouvés",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))})
    })
    @GetMapping("/equipments")
    public List<Equipment> listEquipments() {
        return equipmentDao.findAll();
    }

    @Operation(summary = "Obtenir un équipement par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipement trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))}),
            @ApiResponse(responseCode = "404", description = "Equipement non trouvé",
                    content = @Content)
    })
    @GetMapping("/equipments/{id}")
    public Equipment getEquipment(@Valid @PathVariable int id) {
        return equipmentDao.findById(id).orElseThrow(() -> new EquipmentNotFoundException("Héros non trouvé avec l'identifiant " + id));
    }

    @Operation(summary = "Ajouter un équipement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipement créé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))}),
            @ApiResponse(responseCode = "400", description = "Equipement invalide",
                    content = @Content)
    })
    @PostMapping("/equipments")
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment addEquipment(@Valid @RequestBody Equipment equipment) {
        return equipmentDao.save(equipment);
    }

    @Operation(summary = "Modifier un équipement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipement modifié",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))}),
            @ApiResponse(responseCode = "400", description = "Equipement invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipement non trouvé",
                    content = @Content)
    })
    @PutMapping("/equipments/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable int id, @Valid @RequestBody Equipment updatedEquipment) {
        equipmentDao.findById(id).orElseThrow(() -> new EquipmentNotFoundException("Equipement non trouvé avec l'identifiant " + id));
        updatedEquipment.setId(id);
        updatedEquipment.setName(updatedEquipment.getName());
        updatedEquipment.setType(updatedEquipment.getType());
        updatedEquipment.setStrength(updatedEquipment.getStrength());
        final Equipment updated = equipmentDao.save(updatedEquipment);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Supprimer un équipement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipement supprimé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))}),
            @ApiResponse(responseCode = "404", description = "Equipement non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/equipments/{id}")
    public void deleteEquipment(@PathVariable int id) {
        if (!equipmentDao.existsById(id)) {
            throw new EquipmentNotFoundException("Héros non trouvé avec l'identifiant " + id);
        }
        equipmentDao.deleteById(id);
    }

    @Operation(summary = "Obtenir un équipement par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipement trouvé",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipment.class))}),
            @ApiResponse(responseCode = "404", description = "Equipement non trouvé",
                    content = @Content)
    })
    @GetMapping("/equipments/name/{name}")
    public Equipment getEquipmentByName(@PathVariable String name) {
        if (!equipmentDao.existsByName(name)) {
            throw new EquipmentNotFoundException("Equipement non trouvé avec le nom " + name);
        }
        return equipmentDao.findByName(name);
    }


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
