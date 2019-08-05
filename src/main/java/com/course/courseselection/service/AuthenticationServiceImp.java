package com.course.courseselection.service;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentCommand;
import com.course.courseselection.model.StudentResponse;
import com.course.courseselection.repository.StudentRepository;
import com.course.courseselection.security.JwtUtils;
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
    private JwtUtils jwtUtils;

    @Override
    public void register(StudentCommand request) {
        for (Student student : request.getStudents()) {
            StudentData studentData = studentTranslator.translate(student);
            studentRepository.save(studentData);
        }
    }

    @Override
    public StudentResponse login(Student student) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(student.getName(), student.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(student.getName());
        final String token = jwtUtils.generateToken(userDetails);
        return studentTranslator.translate(token);
    }

}
