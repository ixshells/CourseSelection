package com.course.courseselection.service;

import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentRequest;


public interface StudentService {
    void register(StudentRequest request);

    String login(Student student);
}
