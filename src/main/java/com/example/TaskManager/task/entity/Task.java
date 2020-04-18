package com.example.TaskManager.task.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_user")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="task_id")
    private int taskId;

    @Column(name="name")
    @Lob
    @NotBlank
    private String taskName;

    @Column(name="fk_user_id")
    @NotNull
    private int assignedUserId;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime getCreateDateTime;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(int assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getGetCreateDateTime() {
        return getCreateDateTime;
    }

    public void setGetCreateDateTime(LocalDateTime getCreateDateTime) {
        this.getCreateDateTime = getCreateDateTime;
    }
}
