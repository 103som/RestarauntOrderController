package com.example.RestarauntOrderHandler.repository;


import com.example.RestarauntOrderHandler.entity.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Класс по работе с таблицей dish.
 */
@Repository
public interface DishRepo extends CrudRepository<Dish, Long> {
    Dish findByName(String name);

    @Query(value = "SELECT * FROM dish WHERE quantity > 0", nativeQuery = true)
    List<Dish> findAllDishes();
}
