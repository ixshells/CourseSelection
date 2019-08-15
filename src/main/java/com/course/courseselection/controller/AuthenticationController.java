package com.course.courseselection.controller;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.command.StudentResponse;
import com.course.courseselection.command.TeacherCommand;
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

    @RequestMapping(value = "student/register", method = POST)
    @ResponseStatus(value = CREATED)
    public void register(@RequestBody StudentCommand request) {
        studentService.register(request);
    }

    @RequestMapping(value = "student/login", method = POST)
    public StudentResponse login(@RequestBody Student student) {
        return studentService.login(student);
    }

//    @RequestMapping(value = "/teacher/register")
//    public void register(@RequestBody TeacherCommand command) {
//        teacherSear
//    }
}
