package com.example.TaskManager.task.entity;

public interface TaskProjection {
     String getEmail();
     int getTaskId();
     String getTaskName();
     String getCreatedAt();
     String getUpdatedAt();
}
