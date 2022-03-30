package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_record")
public class GroupRecord implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "groupRecordIdSeq", sequenceName = "group_record_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupRecordIdSeq")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group groupId;

    @ManyToOne()
    @JoinColumn(name = "student_login")
    private UserApp studentLogin;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
