package com.server.praktika.controller;

import com.server.praktika.model.UserApp;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userApp")
public class UserAppController {
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @GetMapping("/{login}")
    public ResponseEntity<?> getUsersByLogin(@PathVariable String login, @RequestHeader("Authorization") String jwt) {
        if(jwtUserDetailsService.getRole(jwt).equals("ROLE_TEACHER"))
        {
            return ResponseEntity.ok(userRepository.findFirst50ByLoginIsStartingWith(login));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
     */
}
