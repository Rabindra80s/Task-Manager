package com.example.TaskManager.home.controller;

import com.example.TaskManager.common.model.ApplicationMessage;
import com.example.TaskManager.login.entity.AppUser;
import com.example.TaskManager.login.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "")
    public String getHomePage() {
        System.out.println("Home");
        return "index";
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        System.out.println("register");
        modelAndView.addObject("appUser", new AppUser());
        return modelAndView;
    }

    @GetMapping(value = "/check-email")
    @ResponseBody
    public ApplicationMessage checkEmail(@RequestParam("email") String email) {
        ApplicationMessage applicationMessage = new ApplicationMessage();
        AppUser appUser = appUserService.fetchAppUserByEmail(email);

        if (appUser == null) {
            applicationMessage.setSuccess(true);
        } else {
            applicationMessage.setSuccess(false);
            applicationMessage.setErrorMessage("Email already exists.");
        }

        return applicationMessage;
    }

    @PostMapping(value = "/register")
    public ApplicationMessage processRegisterPage(@ModelAttribute AppUser appUser) {
        ApplicationMessage applicationMessage = new ApplicationMessage();
        AppUser fetchedAppUser = appUserService.fetchAppUserByEmail(appUser.getEmail());

        String hashPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(hashPassword);
        appUser.setRole("USER");

        applicationMessage.setSuccess(true);
        AppUser registeredAppUser = appUserService.addUser(appUser);
        System.out.println("You've successfully registered in our system.");

        return applicationMessage;
    }
}
