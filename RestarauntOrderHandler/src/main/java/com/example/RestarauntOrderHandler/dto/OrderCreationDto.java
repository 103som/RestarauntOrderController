package com.example.RestarauntOrderHandler.dto;

import com.example.RestarauntOrderHandler.models.EStatus;

import java.util.List;

/**
 * Класс, представляющий передаваемые клиентом данные при создании заказа.
 */
public class OrderCreationDto {
    private Integer user_id;

    private Integer dish_count;

    private List<String> dish_list;

    private String special_requests;

    private EStatus status;

    public OrderCreationDto(Integer user_id, Integer dish_count, List<String> dish_list, String special_requests, EStatus status) {
        this.user_id = user_id;
        this.dish_count = dish_count;
        this.dish_list = dish_list;
        this.special_requests = special_requests;
        this.status = status;
    }

    public  OrderCreationDto() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getDish_count() {
        return dish_count;
    }

    public List<String> getDish_list() {
        return dish_list;
    }

    public String getSpecial_requests() {
        return special_requests;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setDish_count(Integer dish_count) {
        this.dish_count = dish_count;
    }

    public void setDish_list(List<String> dish_list) {
        this.dish_list = dish_list;
    }

    public void setSpecial_requests(String special_requests) {
        this.special_requests = special_requests;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
