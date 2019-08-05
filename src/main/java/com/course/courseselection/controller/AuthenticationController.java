package com.course.courseselection.controller;

import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentCommand;
import com.course.courseselection.model.StudentResponse;
import com.course.courseselection.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private AuthenticationService studentService;

    @Autowired
    public AuthenticationController(AuthenticationService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/register", method = POST)
    @ResponseStatus(value = CREATED)
    public void register(@RequestBody StudentCommand request) {
        studentService.register(request);
    }

    @RequestMapping(value = "/login", method = POST)
    public StudentResponse login(@RequestBody Student student) {
        return studentService.login(student);
    }
}
