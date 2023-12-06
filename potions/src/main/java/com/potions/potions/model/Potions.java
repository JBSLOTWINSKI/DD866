package com.potions.potions.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Entity
public class Potions {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "life")
    private int life;


    public Potions() {
    }

    public Potions(int id, String name, int life) {
        this.id = id;
        this.name = name;
        this.life = life;
    }

    @Override
    public String toString() {
        return "utility{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", pv=" + life +
                '}';
    }

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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
