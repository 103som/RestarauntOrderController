package com.example.RestarauntOrderHandler.service;

import com.example.RestarauntOrderHandler.dto.DishCreationDto;
import com.example.RestarauntOrderHandler.dto.DishDeletionDto;
import com.example.RestarauntOrderHandler.dto.OrderCreationDto;
import com.example.RestarauntOrderHandler.entity.Dish;
import com.example.RestarauntOrderHandler.entity.Order;
import com.example.RestarauntOrderHandler.entity.User;
import com.example.RestarauntOrderHandler.exception.*;
import com.example.RestarauntOrderHandler.models.ERole;
import com.example.RestarauntOrderHandler.repository.DishRepo;
import com.example.RestarauntOrderHandler.repository.OrderRepo;
import com.example.RestarauntOrderHandler.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Класс по обработке клиентских запросов.
 * (обрабатывает запросы, связанные со вторым микросервисом).
 * (Используется для того, чтобы не прописывать бизнесс-логику в контроллере).
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private  UserRepo userRepo;

    public void orderCreation(OrderCreationDto orderCreationDto)
            throws UserDoesntExistException, IncorrectDishNameInOrderException, IncorrectOrderException,
                   IncorrectOrderStatusException {

        User user = userRepo.findById(orderCreationDto.getUser_id());
        if (user == null) {
            throw new UserDoesntExistException("Пользователь с таким id не зарегистрирован в системе.");
        }

        if (orderCreationDto.getDish_count() <= 0) {
            throw new IncorrectOrderException("Заказ с таким количеством блюд не может быть создан");
        }

        for (String dish : orderCreationDto.getDish_list()) {
            if (dishRepo.findByName(dish) == null) {
                throw new IncorrectDishNameInOrderException();
            }
        }

        if (orderCreationDto.getStatus() == null) {
            throw new IncorrectOrderStatusException();
        }

        Order order = new Order(user, orderCreationDto.getStatus(), orderCreationDto.getSpecial_requests());
        orderRepo.save(order);
    }

    /**
     *
     * @param dishCreationDto
     * @return true - блюдо добавлено, false - недостаточно прав для добавления
     */
    public boolean addDish(DishCreationDto dishCreationDto) throws IncorrectQuantityException, DishAlreadyExistException {
        if (dishRepo.findByName(dishCreationDto.getName()) != null) {
            throw new DishAlreadyExistException();
        }

        if (dishCreationDto.getRole() != ERole.manager) {
            return false;
        }

        if (dishCreationDto.getQuantity() < 0) {
            throw new IncorrectQuantityException();
        }

        Dish dish = new Dish(dishCreationDto.getName(), dishCreationDto.getDescription(),
                             dishCreationDto.getPrice(), dishCreationDto.getQuantity());

        dishRepo.save(dish);
        return true;
    }

    public boolean deleteDish(DishDeletionDto dishDeletionDto) throws IncorrectDishNameException {
        if (dishDeletionDto.getRole() != ERole.manager) {
            return false;
        }

        Dish dish = dishRepo.findByName(dishDeletionDto.getName());
        if (dish == null) {
            throw new IncorrectDishNameException();
        }

        dishRepo.delete(dish);
        return true;
    }

    public List<Dish> getMenu() {
        return dishRepo.findAllDishes();
    }
}
