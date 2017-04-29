package com.j2clark.service.user;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EurekaClient discoveryClient;
    private final RestTemplate rest;

    @Autowired
    UserServiceImpl(EurekaClient discoveryClient,
                    RestTemplate rest) {
        this.discoveryClient = discoveryClient;
        this.rest = rest;
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyUsers")
    public List<UserService.User> findUsers() {

        InstanceInfo instance = discoveryClient.getNextServerFromEureka("service.user", false);


        String url = instance.getHomePageUrl() + "users";

        logger.info("found remote service url ["+url+"]");


        ResponseEntity<List<UserService.User>> responseEntity =
            rest
                .exchange(url,
                          HttpMethod.GET,
                          null,
                          new ParameterizedTypeReference<List<UserService.User>>() {
                          }
                );

        return responseEntity.getBody();
    }

    public List<UserService.User> emptyUsers() {
        return Collections.emptyList();
    }

}
