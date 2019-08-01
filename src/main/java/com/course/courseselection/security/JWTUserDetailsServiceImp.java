package com.course.courseselection.security;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class JWTUserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentData studentData = studentRepository.findByName(username);
        if (studentData == null) {
            throw new UsernameNotFoundException("student not found");
        }
        return JWTUserFactory.create(studentData);
    }
}
