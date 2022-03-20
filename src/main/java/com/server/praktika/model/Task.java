package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "taskIdSeq", sequenceName = "task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskIdSeq")
    private Integer id;

    ///@Column(name = "teacher_login")
    @ManyToOne()
    @JoinColumn(name = "login_teacher")
    @JsonIgnore
    private UserApp teacherLogin;

    @Column(name = "text_of_task")
    private String textOfTask;

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "name_task")
    private String taskName;

    @Column(name = "task_subject")
    private String taskSubject;

    @OneToMany(mappedBy = "taskId")
    private Collection<TaskFile> attachedFiles;

    @OneToMany(mappedBy = "taskId")
    @JsonIgnore
    private Collection<CurrentTask> currentTasks;

    public Collection<CurrentTask> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(Collection<CurrentTask> currentTasks) {
        this.currentTasks = currentTasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserApp getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(UserApp teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public String getTextOfTask() {
        return textOfTask;
    }

    public void setTextOfTask(String textOfTask) {
        this.textOfTask = textOfTask;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskSubject() {
        return taskSubject;
    }

    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }

    public Collection<TaskFile> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(Collection<TaskFile> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

}
