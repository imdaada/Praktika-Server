package com.server.praktika.repository;

import com.server.praktika.model.TaskFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskFileRepository extends JpaRepository<TaskFile, Integer> {
}
