package com.server.praktika.controller;

import com.server.praktika.config.JwtTokenUtil;
import com.server.praktika.model.JwtRequest;
import com.server.praktika.model.JwtResponse;
import com.server.praktika.model.UserApp;
import com.server.praktika.model.UserAppDTO;
import com.server.praktika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.server.praktika.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserApp user) throws Exception {
        UserApp userApp1 = userRepository.findByLogin(user.getLogin());
        if (userApp1 == null) {
            return ResponseEntity.ok(userDetailsService.save(user));
        }
        return ResponseEntity.badRequest().body("Логин уже занят");
    }

    @GetMapping("/role")
    public ResponseEntity<?> getUserRole (@RequestHeader("Authorization") String jwt) {
        UserAppDTO userAppDTO = new UserAppDTO();
        String role = userDetailsService.getRole(jwt);
        userAppDTO.setRole(role);
        return ResponseEntity.ok().body(userAppDTO);
    }
}
