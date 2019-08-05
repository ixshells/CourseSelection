package com.course.courseselection.service;

import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentCommand;
import com.course.courseselection.model.StudentResponse;


public interface AuthenticationService {
    void register(StudentCommand command);

    StudentResponse login(Student student);
}
