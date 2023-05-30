package com.example.RestarauntOrderHandler.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Класс, проверяющий корректность вводимого пользователем email.
 */
public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}