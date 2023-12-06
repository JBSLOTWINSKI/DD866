package com.game.game.model;

public class Hero {
    private int id;
    private String name;
    private String type;
    private int life;
    private int strength;

    public Hero() {
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", life=" + life +
                ", strength=" + strength +
                '}';
    }
}
