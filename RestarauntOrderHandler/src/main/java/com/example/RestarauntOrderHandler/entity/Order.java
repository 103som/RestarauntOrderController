package com.example.RestarauntOrderHandler.entity;

import com.example.RestarauntOrderHandler.models.ERole;
import com.example.RestarauntOrderHandler.models.EStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status", nullable = false)
    private EStatus status;

    @Column(name = "special_requests", nullable = false)
    private String special_requests;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Order() {
    }

    public Order(User user, EStatus status, String special_requests) {
        this.user = user;
        this.status = status;
        this.special_requests = special_requests;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public EStatus getStatus() {
        return status;
    }

    public String getSpecial_requests() {
        return special_requests;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public void setSpecial_requests(String special_requests) {
        this.special_requests = special_requests;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
