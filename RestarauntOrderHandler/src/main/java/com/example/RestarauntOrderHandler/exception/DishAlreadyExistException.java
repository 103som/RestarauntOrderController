package com.example.RestarauntOrderHandler.exception;

public class DishAlreadyExistException extends Exception {
    public DishAlreadyExistException() {
        super("Блюдо с данным названием уже есть в меню.");
    }
}
