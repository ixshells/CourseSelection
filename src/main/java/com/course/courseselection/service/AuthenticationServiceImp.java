package com.course.courseselection.service;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentRequest;
import com.course.courseselection.repository.StudentRepository;
import com.course.courseselection.security.JWTUtils;
import com.course.courseselection.translator.StudentTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentTranslator studentTranslator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public void register(StudentRequest request) {
        for(Student student: request.getStudents()) {
            StudentData studentData = studentTranslator.translate(student);
            studentRepository.save(studentData);
        }
    }

    @Override
    public String login(Student student) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(student.getName(), student.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(student.getName());
        final String token = jwtUtils.generateToken(userDetails);
        return token;
    }
}
