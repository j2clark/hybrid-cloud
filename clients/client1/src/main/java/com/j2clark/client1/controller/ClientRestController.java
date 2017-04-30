package com.j2clark.client1.controller;

import com.j2clark.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ClientRestController {

    private final UserService userService;

    @Autowired
    private @Value("${client.test:default}") String testValue;

    @Autowired
    public ClientRestController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping("/users/{uid}")
    public UserService.User findUser(@PathVariable("uid") int id) {

        return userService.findUsers()
            .stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .map(user -> user).orElse(null);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from " + testValue;
    }
}
