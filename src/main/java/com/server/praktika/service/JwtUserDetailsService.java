package com.server.praktika.service;

import com.server.praktika.model.UserApp;
import com.server.praktika.model.UserAppDTO;
import com.server.praktika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Добавить получение информации о пльзователе из БД
       /* if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } */
       UserApp userApp = userRepository.findByLogin(username);
       if (userApp == null) {
           throw new UsernameNotFoundException("User not found with username: " + username);
       }
        return new org.springframework.security.core.userdetails.User(userApp.getLogin(), userApp.getPassword(),
                new ArrayList<>());
    }

    public UserApp save(UserAppDTO userAppDTO) {
        UserApp userApp = new UserApp();
        userApp.setFirstName(userAppDTO.getFirstName());
        userApp.setLogin(userAppDTO.getLogin());
        userApp.setPassword(bcryptEncoder.encode(userAppDTO.getPassword()));
        userApp.setRole(userAppDTO.getRole());
        userApp.setSurname(userAppDTO.getSurname());
        return userRepository.save(userApp);
    }
}
