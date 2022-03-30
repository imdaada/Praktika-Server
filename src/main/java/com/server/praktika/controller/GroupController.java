package com.server.praktika.controller;

import com.server.praktika.model.Group;
import com.server.praktika.model.GroupRecord;
import com.server.praktika.model.GroupRecordDTO;
import com.server.praktika.model.Task;
import com.server.praktika.repository.GroupRecordRepository;
import com.server.praktika.repository.GroupRepository;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupRecordRepository groupRecordRepository;

    @GetMapping("")
    public ResponseEntity<?> getListOfGroup(@RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            return ResponseEntity.ok(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getGroups());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask (@RequestBody Group group, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            group.setTeacherLogin(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)));
            return ResponseEntity.ok(groupRepository.save(group));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/addMembr")
    public ResponseEntity<?> addTask (@RequestBody GroupRecordDTO groupRecordDTO, @RequestHeader("Authorization") String jwt) {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<Group> groups = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getGroups();
            for(Group group: groups) {
                if(group.getId().equals(groupRecordDTO.getGroupId().getId())) {
                    GroupRecord groupRecord = new GroupRecord();
                    groupRecord.setGroupId(groupRecordDTO.getGroupId());
                    groupRecord.setStudentLogin(groupRecordDTO.getStudentLogin());
                    return ResponseEntity.ok(groupRecordRepository.save(groupRecord));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteGroup(@RequestBody Group group, @RequestHeader("Authorization") String jwt)  {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<Group> groups = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getGroups();
            for(Group nextGroup: groups) {
                if(group.getId().equals(nextGroup.getId())) {
                    groupRepository.delete(group);
                    return ResponseEntity.ok(new Group());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/deleteMembr")
    public ResponseEntity<?> deleteGroup(@RequestBody GroupRecord groupRecord, @RequestHeader("Authorization") String jwt)  {
        if (jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER")) {
            Collection<Group> groups = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getGroups();
            for(Group group: groups) {
                for (GroupRecord nextGroupRecord: group.getGroupRecords()) {
                    if (groupRecord.getId().equals(nextGroupRecord.getId())) {
                        groupRecordRepository.delete(groupRecord);
                        return ResponseEntity.ok(new GroupRecord());
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
