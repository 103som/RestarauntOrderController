package com.example.RestarauntOrderHandler.controllers;

import com.example.RestarauntOrderHandler.dto.DishCreationDto;
import com.example.RestarauntOrderHandler.dto.DishDeletionDto;
import com.example.RestarauntOrderHandler.dto.OrderCreationDto;
import com.example.RestarauntOrderHandler.entity.Dish;
import com.example.RestarauntOrderHandler.exception.*;
import com.example.RestarauntOrderHandler.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/menu/add/dish")
    public ResponseEntity<String> addDish(@RequestBody DishCreationDto dishCreationDto) {
        try {
            if (orderService.addDish(dishCreationDto)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Блюдо успешно добавлено в меню!");
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Недостаточно прав для выполнения данного действия.");
        } catch (IncorrectQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DishAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/menu/delete/dish")
    public ResponseEntity<String> deleteDish(@RequestBody DishDeletionDto dishDeletionDto) {
        try {
            if (orderService.deleteDish(dishDeletionDto)) {
                return ResponseEntity.status(HttpStatus.OK).body("Блюдо успешно удалено из меню.");
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Недостаточно прав для выполнения данного действия.");
        } catch (IncorrectDishNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/menu")
    public ResponseEntity<String> getMenu() {
        try {
            List<Dish> menu = orderService.getMenu();
            String menuJson = mapper.writeValueAsString(menu);
            return ResponseEntity.ok(menuJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderCreationDto orderCreationDto) {
        try {
            orderService.orderCreation(orderCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно создан!");
        } catch (UserDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IncorrectDishNameInOrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IncorrectOrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IncorrectOrderStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
