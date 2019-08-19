package com.course.courseselection.service;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.command.User;
import com.course.courseselection.command.UserResponse;


public interface AuthenticationService {
    void register(StudentCommand command);

    UserResponse login(Student student);
}
