package com.course.courseselection.controller;

import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentRequest;
import com.course.courseselection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/register", method = POST)
    public void register(@RequestBody StudentRequest request) {
        studentService.register(request);
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestBody Student student) {
        return studentService.login(student);
    }
}
