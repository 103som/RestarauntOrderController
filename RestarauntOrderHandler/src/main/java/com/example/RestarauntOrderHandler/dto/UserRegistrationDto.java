package com.example.RestarauntOrderHandler.dto;

/**
 * Класс, представляющий передаваемые клиентом данные при регистрации.
 */
public class UserRegistrationDto {
    private String username;
    private String email;
    private String password_hash;

    public UserRegistrationDto() {}

    public UserRegistrationDto(String username, String email, String password_hash) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
