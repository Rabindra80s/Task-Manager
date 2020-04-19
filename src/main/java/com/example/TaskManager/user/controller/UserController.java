package com.example.TaskManager.user.controller;

import com.example.TaskManager.common.model.ApplicationMessage;
import com.example.TaskManager.login.entity.AppUser;
import com.example.TaskManager.login.service.AppUserService;
import com.example.TaskManager.task.entity.Task;
import com.example.TaskManager.task.entity.TaskAnswer;
import com.example.TaskManager.task.service.TaskAnswerService;
import com.example.TaskManager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskAnswerService taskAnswerService;

    @GetMapping(value = "")
    public ModelAndView getUserPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        String userEmail = principal.getName();

        AppUser appUser = appUserService.fetchAppUserByEmail(userEmail);
        List<Task> taskList = taskService.fetchTaskByUserId(appUser.getUserId());

        modelAndView.setViewName("user/user");
        modelAndView.addObject("email", userEmail);
        modelAndView.addObject("taskList", taskList);
        modelAndView.addObject("taskSize", taskList.size());
        System.out.println(taskList.size());
        return modelAndView;
    }

    @GetMapping(value = "/view-task/{taskId}")
    public ModelAndView showViewTaskPage(@PathVariable int taskId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        String userEmail = principal.getName();

        Task fetchedTask = taskService.fetchTaskByTaskId(taskId);
        TaskAnswer fetchTaskAnswer = taskAnswerService.findByTaskId(taskId);

        modelAndView.setViewName("user/view-task");
        modelAndView.addObject("email", userEmail);
        modelAndView.addObject("task", fetchedTask);
        modelAndView.addObject("taskAnswer", fetchTaskAnswer);

        return modelAndView;
    }

    @PostMapping(value = "/submit-task")
    @ResponseBody
    public ApplicationMessage processSubmitPage(@RequestBody TaskAnswer taskAnswer) {
        ApplicationMessage applicationMessage = new ApplicationMessage();

        TaskAnswer savedTaskAnswer = null;
        TaskAnswer fetchedTaskAnswer = taskAnswerService.findByTaskId(taskAnswer.getTaskId());

        if (fetchedTaskAnswer == null) {
            savedTaskAnswer = taskAnswerService.createTaskAnswer(taskAnswer);
        } else {
            fetchedTaskAnswer.setAnswer(taskAnswer.getAnswer());
            savedTaskAnswer = taskAnswerService.createTaskAnswer(fetchedTaskAnswer);
        }

        applicationMessage.setSuccess(true);
        if (savedTaskAnswer == null) {
            applicationMessage.setSuccess(false);
            applicationMessage.setErrorMessage("Sorry! Couldn't save task answer");
        }

        return applicationMessage;
    }

}