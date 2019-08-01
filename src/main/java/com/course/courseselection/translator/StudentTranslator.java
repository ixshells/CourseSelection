package com.course.courseselection.translator;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentTranslator {
    public StudentData translate(Student student) {
        LocalDateTime now = LocalDateTime.now();
        StudentData studentData = new StudentData();
        studentData.setName(student.getName());
        studentData.setPassword(student.getPassword());
        studentData.setPasswordResetTime(now);
        return studentData;
    }
}
