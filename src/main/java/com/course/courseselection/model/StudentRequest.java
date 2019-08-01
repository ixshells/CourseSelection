package com.course.courseselection.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private List<Student> students;
}
