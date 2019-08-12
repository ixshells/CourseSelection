package com.course.courseselection.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(String message) {
        super(NOT_FOUND, message);
    }
}
