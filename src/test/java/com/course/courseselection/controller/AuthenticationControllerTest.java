package com.course.courseselection.controller;
import java.util.ArrayList;

import com.course.courseselection.command.Student;
import com.course.courseselection.command.StudentCommand;
import com.course.courseselection.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(MockMvcAutoConfiguration.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AuthenticationControllerTest {

    private static final String JSON_API = "application/vnd.api+json";

    private MockMvc mockMvc;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthenticationController(authenticationService)).build();
    }

    @Test
    @Ignore
    public void should_return_ok_when_register_with_correct_request() throws Exception {
        StudentCommand command = new StudentCommand();
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setName("abc");
        students.add(student);
        student.setPassword("123456");
        command.setStudents(students);
        String content = objectMapper.writeValueAsString(command);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/authentication/register")
                .contentType(JSON_API)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void should_return_correct_token_when_login_with_correct_request() throws Exception {

        StudentCommand command = new StudentCommand();
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setName("abc");
        students.add(student);
        student.setPassword("123456");
        command.setStudents(students);
        String content = objectMapper.writeValueAsString(command);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/authentication/register")
                .contentType(JSON_API)
                .content(content))
                .andExpect(status().isOk());

        String studentContent = objectMapper.writeValueAsString(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login")
                .contentType(JSON_API).content(studentContent))
                .andExpect(status().isOk());
    }
}