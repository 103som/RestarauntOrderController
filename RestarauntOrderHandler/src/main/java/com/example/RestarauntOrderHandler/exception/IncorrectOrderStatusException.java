package com.example.RestarauntOrderHandler.exception;

public class IncorrectOrderStatusException extends  Exception {
    public IncorrectOrderStatusException() {
        super("Некорректный статус заказа.");
    }
}
