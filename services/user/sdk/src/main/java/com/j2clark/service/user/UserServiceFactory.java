package com.j2clark.service.user;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceFactory {

    @Autowired
    private EurekaClient discoveryClient;

    public UserService instance() {
        return new UserServiceImpl(discoveryClient);
    }

    private static class UserServiceImpl implements UserService {

        private final Logger logger = LoggerFactory.getLogger(getClass());
        private final EurekaClient discoveryClient;

        private UserServiceImpl(EurekaClient discoveryClient) {
            this.discoveryClient = discoveryClient;
        }

        // todo: add hystrix!

        @Override
        public List<User> findUsers() {

            InstanceInfo instance = discoveryClient.getNextServerFromEureka("service.user", false);


            String url = instance.getHomePageUrl() + "users";

            logger.info("found remote service url ["+url+"]");


            ResponseEntity<List<User>> responseEntity =
                new RestTemplate()
                    .exchange(url,
                              HttpMethod.GET,
                              null,
                              new ParameterizedTypeReference<List<User>>() {
                              }
                    );

            return responseEntity.getBody();
        }
    }
}
