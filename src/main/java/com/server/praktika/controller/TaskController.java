package com.server.praktika.controller;

import com.server.praktika.model.Task;
import com.server.praktika.model.TaskDTO;
import com.server.praktika.repository.TaskRepository;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
            return ResponseEntity.ok(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks());
        }
        else if (jwtUserDetailsService.getRole(jwt).equals("ROLE_STUDENT")) {
            return ResponseEntity.ok(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getTakenTasks());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        Collection<Task> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks();
        Iterator<Task> iteratorTask = tasks.iterator();
        while (iteratorTask.hasNext()) {
            Task nextTask = iteratorTask.next();
            if (task.getId().equals(nextTask.getId())) {
                taskRepository.delete(nextTask);
                return ResponseEntity.ok(new Task());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask (@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Task task = new Task();
            task.setStudentLogin(taskDTO.getStudentLogin());
            task.setTeacherLogin(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)));
            task.setTextOfTask(taskDTO.getTextOfTask());
            return ResponseEntity.ok(taskRepository.save(task));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeTask (@RequestBody Task task, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<Task> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks();
            Iterator<Task> iteratorTask = tasks.iterator();
            while (iteratorTask.hasNext()) {
                Task nextTask = iteratorTask.next();
                if (task.getId().equals(nextTask.getId())) {
                    return ResponseEntity.ok(taskRepository.save(task));
                }
            }
        }

        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_STUDENT")) {
            Collection<Task> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getTakenTasks();
            Iterator<Task> iteratorTask = tasks.iterator();
            while (iteratorTask.hasNext()) {
                Task nextTask = iteratorTask.next();
                if (task.getId().equals(nextTask.getId())) {
                    nextTask.setAnswerOnTask(task.getAnswerOnTask());
                    nextTask.setTaken(task.getTaken());
                    return ResponseEntity.ok(taskRepository.save(nextTask));
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
