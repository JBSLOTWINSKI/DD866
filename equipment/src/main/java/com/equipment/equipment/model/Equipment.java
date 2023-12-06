package com.equipment.equipment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @NotBlank(message = "Le type de l'équipement est obligatoire")
    @Size(min = 3, max = 25, message = "Le type de l'équipement doit avoir entre 3 et 10 caractères")
    public String type;
    @NotBlank(message = "Le nom de l'équipement est obligatoire")
    @Size(min = 3, max = 25, message = "Le nom de l'équipement doit avoir entre 3 et 20 caractères")
    public String name;
    @NotNull(message = "La force de l'équipement est obligatoire")
    @Min(value = 0, message = "La force de l'équipement doit être au moins 0")
    @Max(value = 50, message = "La force de l'équipement ne peut pas dépasser 10")
    public int strength;

    public Equipment() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", strength=" + strength +
                '}';
    }
}

