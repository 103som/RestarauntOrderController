package com.example.RestarauntOrderHandler.entity;

import com.example.RestarauntOrderHandler.entity.Dish;
import com.example.RestarauntOrderHandler.entity.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_dish")
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    public OrderDish(Order order, Dish dish, Integer quantity, BigDecimal price) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDish() {
    }

    public Integer getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Dish getDish() {
        return dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
