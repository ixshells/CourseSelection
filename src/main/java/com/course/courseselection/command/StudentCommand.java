package com.course.courseselection.command;

import lombok.Data;

import java.util.List;

@Data
public class StudentCommand {
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
