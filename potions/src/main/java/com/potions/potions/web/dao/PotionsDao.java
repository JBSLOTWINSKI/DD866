package com.potions.potions.web.dao;

import com.potions.potions.model.Potions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotionsDao extends JpaRepository<Potions, Integer> {

}
