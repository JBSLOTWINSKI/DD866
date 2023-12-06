package com.game.game.model;

public class Potions {
    private int id;
    private String name;
    private int life;

    public Potions() {
    }

    @Override
    public String toString() {
        return "Potions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", life=" + life +
                '}';
    }
}
