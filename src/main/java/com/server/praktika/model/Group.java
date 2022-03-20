package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "group_st")
public class Group implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "groupIdSeq", sequenceName = "group_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupIdSeq")
    private Integer id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToOne()
    @JoinColumn(name = "teacher_login")
    @JsonIgnore
    private UserApp teacherLogin;

    @OneToMany(mappedBy = "groupId")
    private Collection<GroupRecord> groupRecords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public UserApp getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(UserApp teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public Collection<GroupRecord> getGroupRecords() {
        return groupRecords;
    }

    public void setGroupRecords(Collection<GroupRecord> groupRecords) {
        this.groupRecords = groupRecords;
    }
}
