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

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "teacherLogin")
    private Collection<Task> createdTasks;

    @OneToMany(mappedBy = "teacherLogin")
    private Collection<CurrentTask> emittedTasks;

    @OneToMany(mappedBy = "teacherLogin")
    private Collection<Group> groups;

    @OneToMany(mappedBy = "studentLogin")
    private Collection<CurrentTask> takenTasks;

    @OneToMany(mappedBy = "owner")
    private Collection<TaskFile> files;

    @OneToMany(mappedBy = "studentLogin")
    @JsonIgnore
    private Collection<GroupRecord> groupRecords;

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

    public Collection<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(Collection<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public Collection<TaskFile> getFiles() {
        return files;
    }

    public void setFiles(Collection<TaskFile> files) {
        this.files = files;
    }

    public Collection<CurrentTask> getEmittedTasks() {
        return emittedTasks;
    }

    public void setEmittedTasks(Collection<CurrentTask> emittedTasks) {
        this.emittedTasks = emittedTasks;
    }

    public Collection<CurrentTask> getTakenTasks() {
        return takenTasks;
    }

    public void setTakenTasks(Collection<CurrentTask> takenTasks) {
        this.takenTasks = takenTasks;
    }

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public Collection<GroupRecord> getGroupRecords() {
        return groupRecords;
    }

    public void setGroupRecords(Collection<GroupRecord> groupRecords) {
        this.groupRecords = groupRecords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
