package com.example.TaskManager.user.controller;

import com.example.TaskManager.login.entity.AppUser;
import com.example.TaskManager.login.service.AppUserService;
import com.example.TaskManager.task.entity.Task;
import com.example.TaskManager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "")
    public ModelAndView getUserPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        String userEmail = principal.getName();

        AppUser appUser = appUserService.fetchAppUserByEmail(userEmail);
        List<Task>taskList = taskService.fetchTaskByUserId(appUser.getUserId());

        modelAndView.setViewName("user/user");
        modelAndView.addObject("email", userEmail);
        modelAndView.addObject("taskList", taskList);
        modelAndView.addObject("taskSize", taskList.size());
        System.out.println(taskList.size());
        return modelAndView;
    }
}
