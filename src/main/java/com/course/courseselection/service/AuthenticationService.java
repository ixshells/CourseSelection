package com.course.courseselection.service;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.command.StudentResponse;


public interface AuthenticationService {
    void register(StudentCommand command);

    StudentResponse login(Student student);
}
