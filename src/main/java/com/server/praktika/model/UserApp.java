package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_app")
public class UserApp {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "studentLogin")
    @JsonIgnore
    private Collection<Task> takenTasks;

    @OneToMany(mappedBy = "teacherLogin")
    @JsonIgnore
    private Collection<Task> emittedTasks;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Collection<Task> getTakenTasks() {
        return takenTasks;
    }

    public void setTakenTasks(Collection<Task> takenTasks) {
        this.takenTasks = takenTasks;
    }

    public Collection<Task> getEmittedTasks() {
        return emittedTasks;
    }

    public void setEmittedTasks(Collection<Task> emittedTasks) {
        this.emittedTasks = emittedTasks;
    }
}
