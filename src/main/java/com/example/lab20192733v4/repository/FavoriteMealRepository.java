package com.example.lab20192733v4.repository;


import com.example.lab20192733v4.entity.FavoriteMeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, Integer> {
    boolean existsByIdMeal(String IdMeal);

}
