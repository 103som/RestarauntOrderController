package com.example.RestarauntOrderHandler.dto;

import com.example.RestarauntOrderHandler.models.ERole;

/**
 * Класс, представляющий передаваемые менеджером данные при удалении пункта меню.
 */
public class DishDeletionDto {
    private ERole role;

    private String name;

    public DishDeletionDto(ERole role, String name) {
        this.role = role;
        this.name = name;
    }

    public DishDeletionDto() {
    }

    public ERole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }
}
