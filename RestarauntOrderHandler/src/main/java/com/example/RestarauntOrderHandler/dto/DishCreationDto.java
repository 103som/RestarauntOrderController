package com.example.RestarauntOrderHandler.dto;

import com.example.RestarauntOrderHandler.models.ERole;

import java.math.BigDecimal;

/**
 * Класс, представляющий передаваемые менеджером данные при создании нового пункта меню.
 */
public class DishCreationDto {
    private ERole role;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    public DishCreationDto(ERole role, String name, String description, BigDecimal price, Integer quantity) {
        this.role = role;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public DishCreationDto() {
    }

    public ERole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
