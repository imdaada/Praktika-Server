package com.server.praktika.model;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "taskIdSeq", sequenceName = "task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskIdSeq")
    private Integer id;

    //@Column(name = "student_login")
    @ManyToOne()
    @JoinColumn(name = "login_student")
    private UserApp studentLogin;

    ///@Column(name = "teacher_login")
    @ManyToOne()
    @JoinColumn(name = "login_teacher")
    private UserApp teacherLogin;

    @Column(name = "text_of_task")
    private String textOfTask;

    @Column(name = "is_taken")
    private Boolean isTaken;

    @Column(name = "answer_on_task")
    private String answerOnTask;

    @Column(name = "feedback")
    private String feedback;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTextOfTask() {
        return textOfTask;
    }

    public void setTextOfTask(String textOfTask) {
        this.textOfTask = textOfTask;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
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
}
