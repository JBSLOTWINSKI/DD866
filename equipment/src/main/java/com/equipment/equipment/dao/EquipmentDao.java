package com.equipment.equipment.dao;

import com.equipment.equipment.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDao extends JpaRepository<Equipment, Integer> {
    boolean existsByName(String name);
    Equipment findByName(String name);
}