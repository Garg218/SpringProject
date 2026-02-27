package com.example.ecomProject.controller;


import com.example.ecomProject.model.Users;
import com.example.ecomProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users)
    {
        System.out.println(users);
//        return "success";
          return service.verify(users);
    }

}
