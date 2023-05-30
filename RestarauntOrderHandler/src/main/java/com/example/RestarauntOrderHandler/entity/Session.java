package com.example.RestarauntOrderHandler.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.example.RestarauntOrderHandler.service.JwtService;

/**
 * Класс, представляющий сессию клиента.
 */
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "session_token", nullable = false)
    private String sessionToken;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime cur = LocalDateTime.now();
        expiresAt = cur.plusSeconds(JwtService.ACCESS_TOKEN_EXPIRATION);
    }

    public Session() {}
    public Session(User user, String sessionToken) {
        this.user = user;
        this.sessionToken = sessionToken;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
