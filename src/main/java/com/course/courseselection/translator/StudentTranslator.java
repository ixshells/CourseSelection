package com.course.courseselection.translator;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentTranslator {
    public StudentData translate(Student student) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LocalDateTime now = LocalDateTime.now();
        StudentData studentData = new StudentData();
        studentData.setName(student.getName());
        studentData.setPassword(encoder.encode(student.getPassword()));
        studentData.setPasswordResetTime(now);
        return studentData;
    }

    public StudentResponse translate(String token) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setToken(token);
        return studentResponse;
    }
}
