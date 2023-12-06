package com.game.game.model;

public class Equipment {
    public int id;
    public String type;
    public String name;
    public int strength;

    public Equipment() {
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