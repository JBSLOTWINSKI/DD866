package com.menu.menu.model;

public class FillHero {

    private String name;
    private String type;
    private int strength;
    private int life;

    public FillHero() {

    }

    public FillHero(String name, String type, int strength, int life) {
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.life = life;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
