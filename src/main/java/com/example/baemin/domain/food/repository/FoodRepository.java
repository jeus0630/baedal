package com.example.baemin.domain.food.repository;

import com.example.baemin.domain.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByIsActive(Boolean isActive);
}
