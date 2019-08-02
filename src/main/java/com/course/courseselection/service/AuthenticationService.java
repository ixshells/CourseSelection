package com.course.courseselection.service;

import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentRequest;


public interface AuthenticationService {
    void register(StudentRequest request);

    String login(Student student);
}
