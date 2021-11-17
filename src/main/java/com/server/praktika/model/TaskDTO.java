package com.server.praktika.model;

public class TaskDTO {
    private UserApp studentLogin;
    private UserApp teacherLogin;
    private String textOfTask;
    private Boolean isTaken;
    private String answerOnTask;
    private String feedback;

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
