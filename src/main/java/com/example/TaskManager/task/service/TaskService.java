package com.example.TaskManager.task.service;

import com.example.TaskManager.task.entity.Task;
import com.example.TaskManager.task.entity.TaskProjection;
import com.example.TaskManager.task.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task>getAllTasks() {
        return taskRepository.findAll();
    }

    public List<TaskProjection>fetchAllTasksWithUserInfo() {
        return taskRepository.getAllTasksWithUserInfo();
    }

   public List<Task>fetchTaskByUserId(int userId) {
       return taskRepository.fetchTaskByUserId(userId);
   }

    public Task fetchTaskByTaskId(int taskId) {
        return taskRepository.fetchTaskByTaskId(taskId);
    }

}
