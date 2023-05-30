package com.example.RestarauntOrderHandler.repository;

import com.example.RestarauntOrderHandler.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Класс по работе с таблицей user_info.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    //@Query(value = "SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
    User findByEmail(String email);

    User findById(Integer id);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.passwordHash = :passwordHash")
    User findByEmailAndPassword(@Param("email") String email, @Param("passwordHash") String passwordHash);
}
