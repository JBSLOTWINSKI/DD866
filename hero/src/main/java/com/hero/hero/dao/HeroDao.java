package com.hero.hero.dao;

import com.hero.hero.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroDao extends JpaRepository<Hero, Integer> {

}
