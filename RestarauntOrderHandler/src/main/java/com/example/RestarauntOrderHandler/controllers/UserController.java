package com.example.RestarauntOrderHandler.controllers;
import com.example.RestarauntOrderHandler.dto.UserAuthorizationDto;
import com.example.RestarauntOrderHandler.dto.UserRegistrationDto;
import com.example.RestarauntOrderHandler.entity.Session;
import com.example.RestarauntOrderHandler.entity.User;
import com.example.RestarauntOrderHandler.exception.IncorrectEmailException;
import com.example.RestarauntOrderHandler.exception.IncorrectTokenException;
import com.example.RestarauntOrderHandler.exception.UserAlreadyExistException;
import com.example.RestarauntOrderHandler.exception.UserDoesntExistException;
import com.example.RestarauntOrderHandler.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.lang.String;

/**
 * Класс контроллер, обрабатывающий HTTP запросы, связанные с
 * регистрауией, авторизацией и получением данных по JWT токену.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userDto) {
        try {
            userService.registration(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно зарегестрирован");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IncorrectEmailException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserAuthorizationDto userDto) {
        try {
            Session session = userService.authorization(userDto);
            String message = "Пользователь успешно авторизован";
            String response = "{\n" +
                    "  \"message\": \"" + message + "\",\n" +
                    "  \"token\": \"" + session.getSessionToken() + "\"\n" +
                    "}";
            return ResponseEntity.ok(response);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IncorrectEmailException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/info")
    public ResponseEntity<String> GetUserInfo(@RequestHeader("Authorization") String token) {
        try {
            User user = userService.getInfo(token);
            String userJson = mapper.writeValueAsString(user);

            return ResponseEntity.ok(userJson);
        } catch (IncorrectTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/users")
    public ResponseEntity test() {
        try {
            return ResponseEntity.ok("Сервер работает!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
