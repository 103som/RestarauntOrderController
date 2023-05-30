package com.example.RestarauntOrderHandler.repository;

import com.example.RestarauntOrderHandler.entity.Session;
import com.example.RestarauntOrderHandler.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс по работе с таблицей session.
 */
@Repository
public interface SessionRepo extends CrudRepository<Session, Long> {
    Session findByUser(User user);

    @Query("SELECT s.user FROM Session s WHERE s.sessionToken = :token")
    User findByToken(String token);
}
