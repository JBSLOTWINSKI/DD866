package com.game.game.model;

public class Enemy {
    private int id;
    private String name;
    private String type;
    private int life;
    private int strength;

    public Enemy() {
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