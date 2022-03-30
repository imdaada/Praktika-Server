package com.server.praktika.controller;

import com.server.praktika.model.Task;
import com.server.praktika.model.TaskDTO;
import com.server.praktika.repository.TaskRepository;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.JwtUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getListOfTasks(@RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            return ResponseEntity.ok(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getCreatedTasks());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        Collection<Task> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getCreatedTasks();
        for (Task nextTask : tasks) {
            if (task.getId().equals(nextTask.getId())) {
                taskRepository.delete(nextTask);
                return ResponseEntity.ok(new Task());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask (@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            task.setDateCreate(new Date());
            task.setTeacherLogin(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)));
            return ResponseEntity.ok(taskRepository.save(task));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeTask (@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<Task> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getCreatedTasks();
            for (Task nextTask : tasks) {
                if (task.getId().equals(nextTask.getId())) {
                    task = particularUpdate(nextTask, task);
                    return ResponseEntity.ok(taskRepository.save(task));
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private Task particularUpdate(Task task, Task changes) {
        if (changes.getTaskName() != null) {
            task.setTaskName(changes.getTaskName());
        }
        if (changes.getTextOfTask() != null) {
            task.setTextOfTask(changes.getTextOfTask());
        }
        if (changes.getTaskSubject() != null) {
            task.setTaskSubject(changes.getTaskSubject());
        }
        return task;
    }

}
