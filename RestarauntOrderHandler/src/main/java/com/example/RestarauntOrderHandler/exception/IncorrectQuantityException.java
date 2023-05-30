package com.example.RestarauntOrderHandler.exception;

public class IncorrectQuantityException extends  Exception {
    public IncorrectQuantityException() {
        super("Недостаточное количество, доступное для заказа," +
                " чтобы добавить данное блюдо в меню.");
    }
}
