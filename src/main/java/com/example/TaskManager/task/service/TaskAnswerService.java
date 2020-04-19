package com.example.TaskManager.task.service;

import com.example.TaskManager.task.entity.TaskAnswer;
import com.example.TaskManager.task.entity.TaskAnswerProjection;
import com.example.TaskManager.task.repo.TaskAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAnswerService {

    @Autowired
    private TaskAnswerRepository taskAnswerRepository;

    public TaskAnswer createTaskAnswer(TaskAnswer taskAnswer) {
        return taskAnswerRepository.save(taskAnswer);
    }

    public TaskAnswerProjection fetchTaskAnswer(int taskId) {
        return taskAnswerRepository.selectTaskAnswerWithAdditionalInfo(taskId);
    }

    public TaskAnswer findByTaskId(int taskId) {
        return taskAnswerRepository.findByTaskId(taskId);
    }
}
