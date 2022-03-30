package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class CurrentTaskDTO {
    private Integer id;
    private Task taskId;
    private Date dateOfGive;
    private Date dateOfLastChange;
    private boolean isClosed;
    private boolean isTaken;
    private String answerOnTask;
    private String feedback;
    private Date limitDate;
    private UserApp studentLogin;
    private UserApp teacherLogin;
    private boolean isGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    public Date getDateOfGive() {
        return dateOfGive;
    }

    public void setDateOfGive(Date dateOfGive) {
        this.dateOfGive = dateOfGive;
    }

    public Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    public void setDateOfLastChange(Date dateOfLastChange) {
        this.dateOfLastChange = dateOfLastChange;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getAnswerOnTask() {
        return answerOnTask;
    }

    public void setAnswerOnTask(String answerOnTask) {
        this.answerOnTask = answerOnTask;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public UserApp getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(UserApp studentLogin) {
        this.studentLogin = studentLogin;
    }

    public UserApp getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(UserApp teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public boolean isGrade() {
        return isGrade;
    }

    public void setGrade(boolean grade) {
        isGrade = grade;
    }
}
