package com.course.courseselection.exception.advice;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
public class ApiErrors {
    private List<ApiError> errors;

    public ApiErrors(List<ApiError> errors) {
        this.errors = errors;
    }

    public List<ApiError> getErrors() {
        return errors;
    }
}
