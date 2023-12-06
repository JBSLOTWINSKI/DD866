package com.enemy.enemy.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "enemy")
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "id", example = "1", required = true)
    @Column (name = "id", nullable = false)
    @NotNull
    private int id;
    @Schema (name = "name", example = "Massimo")
    @Column (name = "name", nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 40, message = "Le nom doit être compris entre 3 et 40 caractères")
    private String name;

    @Schema (name = "type", example = "Guerrier")
    @Column (name = "type", nullable = false)
    @NotBlank (message = "Le type est obligatoire")
    @Size(min = 3, max = 40, message = "Le type doit être compris entre 3 et 40 caractères")
    private String type;
    @Schema (name = "life", example = "5")
    @Column (name = "life", nullable = false)
    @NotNull
    @Min(value = 5, message = "Les points de vies ne peuvent pas être en-dessous de 5")
    @Max(value = 15, message = "Les points de vies ne peuvent pas être au-dessus de 15")
    @PositiveOrZero(message = "Attention action non autorisée")
    private int life;
    @Schema (name = "strength", example = "10")
    @Column (name = "strength", nullable = false)
    @NotNull
    @Min(value = 1, message = "Les points de force ne peuvent pas être en-dessous de 1")
    @Max(value = 5, message = "Les points de force ne peuvent pas être au-dessus de 5")
    @PositiveOrZero(message = "Attention action non autorisée")
    private int strength;



    // ----------------------------- CONSTRUCTEUR ------------------------------- //


    public Enemy() {

    }

    public Enemy(int id, String name, String type, int life, int strength) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.life = life;
        this.strength = strength;
    }

    // ----------------------------- GETTER & SETTER ------------------------------- //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", life=" + life +
                ", strength=" + strength +
                '}';
    }
}
