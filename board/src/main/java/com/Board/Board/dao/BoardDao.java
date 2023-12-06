package com.Board.Board.dao;

import com.Board.Board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer> {

    @Query("SELECT t FROM Board t WHERE t.idGame=?1")
    List<Board> findByIpGame(int i);

    @Query("DELETE FROM Board t WHERE t.idGame=?1")
    List<Board> deleteByIpGame(int i);
}