package com.menu.menu.controller;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private List board;

    public BoardController() {
        board = new ArrayList();
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < 12; i++) {
            board.add(i);
        }
    }

}
