package com.example.TaskManager.admin.controller;

import com.example.TaskManager.common.model.ApplicationMessage;
import com.example.TaskManager.login.entity.AppUser;
import com.example.TaskManager.login.service.AppUserService;
import com.example.TaskManager.task.entity.Task;
import com.example.TaskManager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "")
    public ModelAndView showAdminPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        String adminEmail = principal.getName();

        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("email", adminEmail);
        return modelAndView;
    }

    @GetMapping(value = "/create-task")
    public ModelAndView showCreateTaskForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        List<AppUser> appUserList = appUserService.getAllUser();

        String adminEmail = principal.getName();

        modelAndView.setViewName("admin/create-task");
        modelAndView.addObject("email", adminEmail);
        modelAndView.addObject("appUserList", appUserList);
        modelAndView.addObject("appUserSize", appUserList.size());
        System.out.println(appUserList.size());

        return modelAndView;
    }

    @PostMapping(value = "/create-task")
    @ResponseBody
    public ApplicationMessage processCreateTask(@Valid @RequestBody Task task) {
        ApplicationMessage applicationMessage = new ApplicationMessage();
        Task createdTask = taskService.createTask(task);

        if (createdTask == null) {
            applicationMessage.setSuccess(false);
            applicationMessage.setErrorMessage("Couldn't create task.");
        } else {
            applicationMessage.setSuccess(true);
            System.out.println("Task successfully created.");
        }

        return applicationMessage;
    }


}
