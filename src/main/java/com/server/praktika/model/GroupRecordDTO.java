package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GroupRecordDTO {
    private Integer id;
    private Group groupId;
    private UserApp studentLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }

    public UserApp getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(UserApp studentLogin) {
        this.studentLogin = studentLogin;
    }
}
