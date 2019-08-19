package com.course.courseselection.exception.advice;

import com.course.courseselection.exception.ApplicationException;
import com.course.courseselection.exception.BadRequestException;
import com.course.courseselection.exception.UnauthorizedException;
import com.course.courseselection.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

//@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<String> handleException(BadRequestException ex) {
//        return new ResponseEntity("", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ApiErrors> handleException(UnauthorizedException ex) {
//        ArrayList<ApiError> errors = new ArrayList<ApiError>();
//        errors.add(newApiError(ex));
//        ApiErrors apiErrors = new ApiErrors(errors);
//        return new ResponseEntity<ApiErrors>(apiErrors, ex.getStatus());
//    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ApiErrors> handleException(UsernameNotFoundException ex) {
//        ArrayList<ApiError> errors = new ArrayList<ApiError>();
//        ApiErrors apiErrors = new ApiErrors(errors);
//        return new ResponseEntity<ApiErrors>(apiErrors, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiErrors> handleException(UserNotFoundException ex) {
//        ArrayList<ApiError> errors = new ArrayList<ApiError>();
//        errors.add(newApiError(ex));
//        ApiErrors apiErrors = new ApiErrors(errors);
//        return new ResponseEntity<ApiErrors>(apiErrors, HttpStatus.NOT_FOUND);
//    }
//
//
//    private ApiError newApiError(ApplicationException ex) {
//        return newApiError(ex.getStatus(), ex.getStatus().getReasonPhrase(), ex.getMessage());
//    }
//
//    private ApiError newApiError(HttpStatus status, String title, String message) {
//        return new ApiError(status.toString(), "", title, message);
//    }
}
