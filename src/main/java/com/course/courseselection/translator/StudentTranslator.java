package com.course.courseselection.translator;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.UserResponse;
import com.course.courseselection.entity.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentTranslator {

    @Autowired
    private UserTranslator userTranslator;

    public StudentData translate(Student student) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LocalDateTime now = LocalDateTime.now();
        StudentData studentData = new StudentData();
        studentData.setName(student.getName());
        studentData.setPassword(encoder.encode(student.getPassword()));
        studentData.setPasswordResetTime(now);
        studentData.setCreatedTime(now);
        return studentData;
    }

    public UserResponse translate(String token) {
        return userTranslator.translate(token);
    }
}
