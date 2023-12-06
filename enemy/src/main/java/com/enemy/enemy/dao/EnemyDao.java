package com.enemy.enemy.dao;

import com.enemy.enemy.model.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyDao extends JpaRepository<Enemy, Integer> {

}
