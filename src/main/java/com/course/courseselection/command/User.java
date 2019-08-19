package com.course.courseselection.command;

import lombok.Data;

@Data
public class User {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
