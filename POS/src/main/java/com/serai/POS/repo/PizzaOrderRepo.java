package com.serai.POS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serai.POS.model.PizzaOrder;

/**
 * Pizza order repository interface
 * 
 * Used to access common JPA queries performed by extended the JpaRepository
 * Common methods such as, getById, deleteById, save and findAll
 * 
 * @author Brian
 *
 */
@Repository
public interface PizzaOrderRepo extends JpaRepository<PizzaOrder, Integer> {
}
