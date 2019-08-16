package com.course.courseselection.security;

import com.course.courseselection.authentication.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtStudentAuthenticationTokenFilter extends AuthenticationTokenFilter {

    @Autowired
    public JwtStudentAuthenticationTokenFilter(StudentUserDetailsServiceImp userDetailsService) {
        super(userDetailsService);
    }
}

