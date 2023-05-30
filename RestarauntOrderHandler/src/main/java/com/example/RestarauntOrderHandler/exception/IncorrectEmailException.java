package com.example.RestarauntOrderHandler.exception;

public class IncorrectEmailException extends  Exception {
    public IncorrectEmailException() {
        super("Неправильно указан email(email содержит недопустимые символы).");
    }
}
