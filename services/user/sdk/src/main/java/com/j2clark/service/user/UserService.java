package com.j2clark.service.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public interface UserService {

    List<User> findUsers();

    @JsonIgnoreProperties(ignoreUnknown = true)
    class User {
        private final int id;
        private final String first;
        private final String last;

        @JsonCreator
        public User(@JsonProperty("id") int id,
                    @JsonProperty("first") String first,
                    @JsonProperty("last") String last) {
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
