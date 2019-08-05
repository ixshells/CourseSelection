package com.course.courseselection.controller;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void register(@RequestBody StudentCommand request) {
        studentService.register(request);
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestBody Student student) {
        return studentService.login(student);
    }
}
