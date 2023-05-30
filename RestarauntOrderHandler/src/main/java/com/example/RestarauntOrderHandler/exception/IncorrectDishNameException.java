package com.example.RestarauntOrderHandler.exception;

public class IncorrectDishNameException extends  Exception {
    public IncorrectDishNameException() {
        super("В меню нет блюда с данным названием.");
    }
}
