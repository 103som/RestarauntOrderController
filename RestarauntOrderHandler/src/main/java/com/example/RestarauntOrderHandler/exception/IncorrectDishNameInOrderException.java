package com.example.RestarauntOrderHandler.exception;

public class IncorrectDishNameInOrderException extends  Exception {
    public IncorrectDishNameInOrderException() {
        super("Неправильно передан список блюд," +
                " одно из переданных блюд отсутствует в меню.");
    }
}
