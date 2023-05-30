package com.example.RestarauntOrderHandler.service;
import com.example.RestarauntOrderHandler.exception.IncorrectTokenException;
import io.jsonwebtoken.*;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * Класс по работе с JWT-токеном.
 */
@Component
public class JwtService {
    // Предварительно сгенерированный секретный ключ.
    private static final String SECRET_KEY = "vekqtqSqNQs6FfHICTt2WqzbNAzjtmwVksVPWKRzlW+zwv15EV/A0InB0cMemHLxG5G8yd27D2bQKwSUGUT8eg==";
    public static final long ACCESS_TOKEN_EXPIRATION = 3600000; // 1 час
    public static final long REFRESH_TOKEN_EXPIRATION = 604800000; // 1 неделя

    public static String generateAccessToken(Integer userId) {
        return generateToken(userId, ACCESS_TOKEN_EXPIRATION);
    }

    // генерация refresh-токена.
    public static String generateRefreshToken(Integer userId) {
        return generateToken(userId, REFRESH_TOKEN_EXPIRATION);
    }

    // генерация access-токена(т.к. по заданию не используем refresh-токен).
    private static String generateToken(Integer userId, long expiration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration); // Токен будет действителен в течение часа.

        try {
            return Jwts.builder()
                    .setSubject(userId.toString())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        } catch (UnsupportedJwtException ex) {
            // Обработка не поддерживаемого алгоритма подписи
            ex.printStackTrace();
            return null;
        } catch (IllegalArgumentException ex) {
            // Обработка недопустимого аргумента, например, нулевого ключа или неверного алгоритма
            ex.printStackTrace();
            return null;
        } catch (SecurityException ex) {
            // Обработка ошибки безопасности при подписи
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            // Обработка других исключений
            ex.printStackTrace();
            return null;
        }
    }

    // Проверка access токена на корректность(интерфейс).
    static public boolean validateAccessToken(String accessToken) throws IncorrectTokenException {
        return validateToken(accessToken);
    }

    // проверка refresh токена на корректность(интерфейс).
    static public boolean validateRefreshToken(String refreshToken) throws IncorrectTokenException {
        return validateToken(refreshToken);
    }

    // проверка токена на корректность.
    static private boolean validateToken(String token) throws IncorrectTokenException {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            throw new IncorrectTokenException("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            throw new IncorrectTokenException("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            throw new IncorrectTokenException("Malformed jwt");
        } catch (SignatureException sEx) {
            throw new IncorrectTokenException("Invalid signature");
        } catch (Exception e) {
            throw new IncorrectTokenException("Error with token");
        }
    }
}
