package com.rasitesdmr.springbootreactive.controller;

import com.rasitesdmr.springbootreactive.model.User;
import com.rasitesdmr.springbootreactive.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    int a = 0;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String createUser() {
        System.out.printf("Thread %s Started ---- Count : %s%n",Thread.currentThread().getId(),a++);
        userService.createUser();
        System.out.printf("Thread %s Stop %n",Thread.currentThread().getId());
        return "Kayıt işlemi tamamlandı";
    }

    @GetMapping("")
    public List<User> getAllUser() {
        System.out.printf("Thread %s Started ---- Count : %s%n",Thread.currentThread().getId(),a++);
        var a = userService.getAllUsers();
        System.out.printf("Thread %s Stop %n",Thread.currentThread().getId());
        return a;
    }

}
