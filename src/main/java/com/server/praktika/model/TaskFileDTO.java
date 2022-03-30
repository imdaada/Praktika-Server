package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TaskFileDTO {
    private Integer id;
    private Task taskId;
    private CurrentTask currentTaskId;

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

    public CurrentTask getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(CurrentTask currentTaskId) {
        this.currentTaskId = currentTaskId;
    }
}
