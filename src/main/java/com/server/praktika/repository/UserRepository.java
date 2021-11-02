package com.server.praktika.repository;

import com.server.praktika.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserApp, String> {
    UserApp findByLogin(String firstName);
}
