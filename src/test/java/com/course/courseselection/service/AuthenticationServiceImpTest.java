package com.course.courseselection.service;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.command.StudentResponse;
import com.course.courseselection.repository.StudentRepository;
import com.course.courseselection.security.JwtUtils;
import com.course.courseselection.translator.StudentTranslator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceImpTest {

    @InjectMocks
    private AuthenticationServiceImp authenticationService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentTranslator studentTranslator;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    public void should_register_student_correctly_with_command() {
        StudentCommand command = new StudentCommand();

        ArrayList<Student> students = new ArrayList<>();
        Student student = buildStudent();
        students.add(student);

        StudentData studentData = new StudentData();
        when(studentTranslator.translate(student)).thenReturn(studentData);

        command.setStudents(students);
        authenticationService.register(command);

        verify(studentRepository).save(studentData);
    }

    @Test
    public void should_login_success_with_correct_student() {
        Student student = buildStudent();
        String expectedToken = "token";
        when(jwtUtils.generateToken(any())).thenReturn(expectedToken);
        StudentResponse response = authenticationService.login(student);
        Assert.assertEquals(expectedToken, response.getToken());
    }

    private Student buildStudent() {
        Student student = new Student();
        student.setName("name");
        student.setPassword("password");
        return student;
    }
}