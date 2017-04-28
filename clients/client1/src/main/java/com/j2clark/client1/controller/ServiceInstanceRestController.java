package com.j2clark.client1.controller;

import com.j2clark.service.user.UserService;
import com.j2clark.service.user.UserServiceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInstanceRestController {

    @Autowired
    UserServiceFactory userService;

    @RequestMapping("/users/{uid}")
    public UserService.User findUser(@PathVariable("uid") int id) {
        return userService.instance()
            .findUsers()
            .stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .map(user -> user).orElse(null);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Client1";
    }

}
