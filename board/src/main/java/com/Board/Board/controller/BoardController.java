package com.Board.Board.controller;

import com.Board.Board.dao.BoardDao;
import com.Board.Board.exception.ResourceNotFoundException;
import com.Board.Board.model.Board;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BoardController {

    private final BoardDao boardDao;


    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @PostMapping(value = "/board")
    public void addNewBoard() {
        for (int i = 1; i < 11; i++) {
            Board objEmp = new Board();
            objEmp.setIdGame(100);
            objEmp.setCaseNum(i);
            objEmp.setIdHero(0);
            objEmp.setIdEnemy(0);
            objEmp.setIdEquipment(0);
            if (objEmp.getCaseNum()==2){
                objEmp.setIdPotion(1);
            } else {
                objEmp.setIdPotion(0);
            }
            boardDao.save(objEmp);
        }
    }

    @GetMapping(value = "/current-board/{idGame}")
    private List<Board> GetGame(@PathVariable int idGame) {
        return boardDao.findByIpGame(idGame);
    }

    @GetMapping(value = "/current-case/foo")
    private Board GetCase(@RequestParam int caseNum){
        return boardDao.findCase(caseNum);
    }

    @DeleteMapping("/current-board/{idGame}")
    private void deleteGame(@PathVariable int idGame) {
        List<Board> board = boardDao.findByIpGame(idGame);
        boardDao.deleteAll(board);
    }
}