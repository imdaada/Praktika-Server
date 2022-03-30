package com.server.praktika.controller;

import com.server.praktika.model.CurrentTask;
import com.server.praktika.model.CurrentTaskDTO;
import com.server.praktika.model.Task;
import com.server.praktika.repository.CurrentTaskRepository;
import com.server.praktika.repository.TaskFileRepository;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/currenttask")
public class CurrentTaskController {
    @Autowired
    TaskFileRepository taskFileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    CurrentTaskRepository currentTaskRepository;

    @GetMapping("")
    public ResponseEntity<?> getListOfTasks(@RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<CurrentTask> currentTasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks();
            currentTasks.forEach(currentTask -> {
                if(currentTask.getLimitDate().before(new Date())) {
                    currentTask.setClosed(true);
                }
            });
            return ResponseEntity.ok(currentTasks);
        }
        else if (jwtUserDetailsService.getRole(jwt).equals("ROLE_STUDENT")) {
            Collection<CurrentTask> currentTasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getTakenTasks();
            currentTasks.forEach(currentTask -> {
                if(currentTask.getLimitDate().before(new Date())) {
                    currentTask.setClosed(true);
                }
            });
            return ResponseEntity.ok(currentTasks);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestBody CurrentTask currentTask, @RequestHeader("Authorization") String jwt) {
        Collection<CurrentTask> currentTasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks();
        for (CurrentTask nextTask : currentTasks) {
            if (currentTask.getId().equals(nextTask.getId())) {
                currentTaskRepository.delete(nextTask);
                return ResponseEntity.ok(new CurrentTask());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask (@RequestBody CurrentTaskDTO currentTaskDTO, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            if (userRepository.findByLogin(currentTaskDTO.getStudentLogin().getLogin()).getRole()
                    .equals("ROLE_TEACHER")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            CurrentTask currentTask = new CurrentTask();
            currentTask.setTeacherLogin(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)));
            currentTask.setDateOfGive(new Date());
            currentTask.setDateOfLastChange(new Date());
            currentTask.setTaken(currentTaskDTO.isTaken());
            currentTask.setAnswerOnTask(currentTaskDTO.getAnswerOnTask());
            currentTask.setLimitDate(currentTaskDTO.getLimitDate());
            currentTask.setStudentLogin(currentTaskDTO.getStudentLogin());
            currentTask.setClosed(currentTaskDTO.isClosed());
            currentTask.setGrade(currentTaskDTO.isGrade());
            currentTask.setFeedback(currentTaskDTO.getFeedback());
            currentTask.setTaskId(currentTaskDTO.getTaskId());
            return ResponseEntity.ok(currentTaskRepository.save(currentTask));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeTask (@RequestBody CurrentTask currentTask, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<CurrentTask> currentTasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getEmittedTasks();
            for (CurrentTask nextTask : currentTasks) {
                if (currentTask.getId().equals(nextTask.getId())) {
                    currentTask = particularUpdateTeacher(nextTask, currentTask);
                    currentTask.setDateOfLastChange(new Date());
                    return ResponseEntity.ok(currentTaskRepository.save(currentTask));
                }
            }
        }

        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_STUDENT")) {
            Collection<CurrentTask> currentTasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getTakenTasks();
            for (CurrentTask nextTask : currentTasks) {
                if (currentTask.getId().equals(nextTask.getId())) {
                    currentTask = particularUpdateStudent(nextTask, currentTask);
                    currentTask.setDateOfLastChange(new Date());
                    return ResponseEntity.ok(currentTaskRepository.save(currentTask));
                }
            }
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private CurrentTask particularUpdateTeacher(CurrentTask currentTask, CurrentTask changes) {
        if (changes.getAnswerOnTask() != null) {
            currentTask.setAnswerOnTask(changes.getAnswerOnTask());
        }
        if (changes.getFeedback() != null) {
            currentTask.setFeedback(changes.getFeedback());
        }
        if (changes.getLimitDate() != null) {
            currentTask.setLimitDate(changes.getLimitDate());
        }
        currentTask.setClosed(changes.isClosed());
        currentTask.setGrade(changes.isGrade());
        return currentTask;
    }
    private CurrentTask particularUpdateStudent(CurrentTask currentTask, CurrentTask changes) {
        if (changes.getAnswerOnTask() != null) {
            currentTask.setAnswerOnTask(changes.getAnswerOnTask());
        }
        currentTask.setTaken(changes.isTaken());
        return currentTask;
    }

}
