package com.server.praktika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "task_file")
public class TaskFile {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "taskFileIdSeq", sequenceName = "task_file_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskFileIdSeq")
    private Integer id;

    @Nullable
    @ManyToOne()
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task taskId;

    @Nullable
    @ManyToOne()
    @JoinColumn(name = "current_task_id")
    @JsonIgnore
    private CurrentTask currentTaskId;

    @Column(name = "link")
    private String link;

    @Column(name = "f_name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "owner")
    @JsonIgnore
    private UserApp owner;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UserApp getOwner() {
        return owner;
    }

    public void setOwner(UserApp owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
