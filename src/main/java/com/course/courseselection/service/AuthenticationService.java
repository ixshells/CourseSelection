package com.course.courseselection.service;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;


public interface AuthenticationService {
    void register(StudentCommand command);

    String login(Student student);
}
