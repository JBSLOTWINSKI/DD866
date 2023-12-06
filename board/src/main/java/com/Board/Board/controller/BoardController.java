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

    @PostMapping(value ="/board")
    private ResponseEntity<Board> addOneCase(@Valid @RequestBody Board board) {
        Board boardCaseAdded = boardDao.save(board);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(boardCaseAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(boardCaseAdded);
    }

    @PostMapping(value = "/add-new-board/{n}")
    public void addNewBoard(@PathVariable("n") int n) {
        for (int i = 1; i < n; i++) {
            Board objEmp = new Board();
            objEmp.setIdGame(200);
            objEmp.setCaseNum(i);
            objEmp.setIdHero(0);
            objEmp.setIdEnemy(0);
            objEmp.setIdEquipment(0);
            objEmp.setIdPotion(0);

            String uri = "http://localhost:8085/board";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(uri, objEmp, Board.class);

        }
    }
    @PutMapping("/update-board/{id}")
    private ResponseEntity<Board> updateBoard(@PathVariable int id,@RequestBody Board board) {
        Board updateBoard = boardDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id: " + id));
        updateBoard.setIdPotion(board.getIdPotion());
        updateBoard.setIdPotion(board.getIdEnemy());
        updateBoard.setIdPotion(board.getIdEquipment());
        boardDao.save(updateBoard);
        return ResponseEntity.ok(updateBoard);
    }

//    @PutMapping(value = "/current-board/{idGame}")
//    public ResponseEntity<Board> modifyBoard(@PathVariable int idGame){
//        List<Board> board = boardDao.findByIpGame(idGame);
//
//    }



    @GetMapping(value = "/current-board/{idGame}")
    private List<Board> GetGame(@PathVariable int idGame) {
        return boardDao.findByIpGame(idGame);
    }

    @DeleteMapping("/current-board/{idGame}")
    private void deleteGame(@PathVariable int idGame) {
        List<Board> board = boardDao.findByIpGame(idGame);
        boardDao.deleteAll(board);
    }
}