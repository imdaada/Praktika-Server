package com.server.praktika.repository;

import com.server.praktika.model.CurrentTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentTaskRepository extends JpaRepository<CurrentTask, Integer> {
}
