package com.example.TaskManager.login.service;

import com.example.TaskManager.login.entity.AppUser;
import com.example.TaskManager.login.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

@Autowired
    private AppUserRepository appUserRepository;

public AppUser fetchAppUserByEmail(String email) {
    return appUserRepository.findByEmail(email);
}
}
