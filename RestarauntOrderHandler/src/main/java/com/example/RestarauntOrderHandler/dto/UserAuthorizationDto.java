package com.example.RestarauntOrderHandler.dto;

/**
 * Класс, представляющий передаваемые клиентом данные при авторизации.
 */
public class UserAuthorizationDto {
    private String email;
    private String password_hash;

    public UserAuthorizationDto() {}
    public UserAuthorizationDto(String email, String password_hash) {
        this.email = email;
        this.password_hash = password_hash;
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
