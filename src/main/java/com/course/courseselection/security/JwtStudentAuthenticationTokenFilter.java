package com.course.courseselection.security;

import com.course.courseselection.authentication.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter(urlPatterns = "/student/**")
public class JwtStudentAuthenticationTokenFilter extends AuthenticationTokenFilter {

    @Autowired
    public JwtStudentAuthenticationTokenFilter(StudentUserDetailsServiceImp userDetailsService) {
        super(userDetailsService, new AntPathRequestMatcher("/student/**"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
    }
}

