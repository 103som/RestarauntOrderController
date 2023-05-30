package com.example.RestarauntOrderHandler.repository;

import com.example.RestarauntOrderHandler.entity.Order;
import com.example.RestarauntOrderHandler.entity.Session;
import com.example.RestarauntOrderHandler.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Класс по работе с таблицей order.
 */
@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
