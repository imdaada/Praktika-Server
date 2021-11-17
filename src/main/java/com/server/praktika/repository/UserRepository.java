package com.server.praktika.repository;

import com.server.praktika.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserApp, String> {
    UserApp findByLogin(String firstName);
    List<UserApp> findFirst50ByLoginIsStartingWith(String login);
}
