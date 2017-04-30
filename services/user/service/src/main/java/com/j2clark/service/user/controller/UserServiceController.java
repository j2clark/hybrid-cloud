package com.j2clark.service.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RefreshScope
@RestController
public class UserServiceController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // mutable properties
    @Autowired
    private @Value("${user-service.testValue:default}") String testValue;

    @RequestMapping("/users")
    public List<User> getUsers() {

        logger.info("user request");

        return Arrays.asList(new User(1, "Jamie", "Clark"));
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from " + testValue;
    }

    public static class User {
        private final int id;
        private final String first;
        private final String last;

        public User(int id, String first, String last) {
            this.id = id;
            this.first = first;
            this.last = last;
        }

        public int getId() {
            return id;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }
}
