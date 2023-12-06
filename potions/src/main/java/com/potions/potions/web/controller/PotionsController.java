package com.potions.potions.web.controller;

import com.potions.potions.exception.ResourceNotFoundException;
import com.potions.potions.model.Potions;
import com.potions.potions.web.dao.PotionsDao;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.*;

@RestController
public class PotionsController {

    private final PotionsDao potionsDao;


    public PotionsController(PotionsDao potionsDao) {
        this.potionsDao = potionsDao;
    }

    @GetMapping("Utility")
    public List<Potions> listUtility() {
        return potionsDao.findAll();
    }


    @GetMapping(value = "/Utility/{id}")
    public Optional<Potions> findUtilityById (@PathVariable int id) {
        return potionsDao.findById(id);
    }

    @PostMapping(value ="/Utility")
    public ResponseEntity<Potions> addUtility(@RequestBody Potions potion){
        Potions utilityAdded = potionsDao.save(potion);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(utilityAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(utilityAdded);
    }

    @PutMapping("/Utility/{id}")
    public ResponseEntity<Potions> updateEmployee(@PathVariable int id,@RequestBody Potions potionsdetails) {
        Potions updatePotion = potionsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        updatePotion.setName(potionsdetails.getName());
        updatePotion.setLife(potionsdetails.getLife());
        potionsDao.save(updatePotion);
        return ResponseEntity.ok(updatePotion);
    }

    @DeleteMapping("/Utility/{id}")
    public void deleteUtility(@PathVariable int id){
        potionsDao.deleteById(id);
    }
}

