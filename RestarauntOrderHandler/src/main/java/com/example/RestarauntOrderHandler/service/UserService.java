package com.example.RestarauntOrderHandler.service;

import com.example.RestarauntOrderHandler.dto.UserAuthorizationDto;
import com.example.RestarauntOrderHandler.dto.UserRegistrationDto;
import com.example.RestarauntOrderHandler.entity.Session;
import com.example.RestarauntOrderHandler.entity.User;
import com.example.RestarauntOrderHandler.exception.IncorrectEmailException;
import com.example.RestarauntOrderHandler.exception.IncorrectTokenException;
import com.example.RestarauntOrderHandler.exception.UserAlreadyExistException;
import com.example.RestarauntOrderHandler.models.ERole;
import com.example.RestarauntOrderHandler.exception.UserDoesntExistException;
import com.example.RestarauntOrderHandler.repository.SessionRepo;
import com.example.RestarauntOrderHandler.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс по обработке клиентских запросов.
 * (обрабатывает запросы на авторизацию, регистрацию, получение данных по JWT токену).
 * (Используется для того, чтобы не прописывать бизнесс-логику в контроллере).
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SessionRepo sessionRepo;

    public User registration(UserRegistrationDto userDto) throws UserAlreadyExistException, IncorrectEmailException {
        if (!EmailValidator.validate(userDto.getEmail())) {
            throw new IncorrectEmailException();
        }

        if (userRepo.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже зарегестрирован");
        }

        if (userRepo.findByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким email уже зарегестрирован");
        }


        User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword_hash(), ERole.customer);
        return userRepo.save(user);
    }

    public Session authorization(UserAuthorizationDto userDto) throws IncorrectEmailException, UserDoesntExistException {
        if (!EmailValidator.validate(userDto.getEmail())) {
            throw new IncorrectEmailException();
        }
        User user = userRepo.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword_hash());
        if (user == null) {
            throw new UserDoesntExistException("Пользователя с заданными(email/password) не существует.");
        }

        Session session = sessionRepo.findByUser(user);
        if (session == null) {
            String token = JwtService.generateAccessToken(user.getId());
            session = new Session(user, token);
            return sessionRepo.save(session);
        }

        return session;
    }

    // Получение информации о пользователе по JWT токену.
    public User getInfo(String token) throws IncorrectTokenException {
        if (JwtService.validateAccessToken(token)) {
            User user = sessionRepo.findByToken(token);
            if (user == null) {
                throw new IncorrectTokenException("Указанный токен не найден.");
            }

            return user;
        }

        throw new IncorrectTokenException("Передан некорректный токен.");
    }
}
