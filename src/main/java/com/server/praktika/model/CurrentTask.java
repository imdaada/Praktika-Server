package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "current_task")
public class CurrentTask {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "currentTaskIdSeq", sequenceName = "current_task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currentTaskIdSeq")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task taskId;

    @Column(name = "date_of_give")
    private Date dateOfGive;

    @Column(name = "date_of_change")
    private Date dateOfLastChange;

    @Column(name = "is_closed")
    private boolean isClosed;

    @Column(name = "is_taken")
    private boolean isTaken;

    @Nullable
    @Column(name = "answer_on_task")
    private String answerOnTask;

    @Nullable
    @Column(name = "feedback")
    private String feedback;

    @Nullable
    @Column(name = "limit_date")
    private Date limitDate;

    @ManyToOne()
    @JoinColumn(name = "student_login")
    @JsonIgnore
    private UserApp studentLogin;

    @ManyToOne()
    @JoinColumn(name = "teacher_login")
    @JsonIgnore
    private UserApp teacherLogin;

    @Nullable
    @Column(name = "is_grade")
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
