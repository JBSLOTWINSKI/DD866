package com.Board.Board.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private int idGame;
    private int caseNum;

    private int idHero;

    private int idEnemy;
    private int idEquipment;
    private int idPotion;

    public Board() {
    }

    public int getIdPotion() {
        return idPotion;
    }

    public void setIdPotion(int idPotion) {
        this.idPotion = idPotion;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(int caseNum) {
        this.caseNum = caseNum;
    }

    public int getIdHero() {
        return idHero;
    }

    public void setIdHero(int idHero) {
        this.idHero = idHero;
    }

    public int getIdEnemy() {
        return idEnemy;
    }

    public void setIdEnemy(int idEnemy) {
        this.idEnemy = idEnemy;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}