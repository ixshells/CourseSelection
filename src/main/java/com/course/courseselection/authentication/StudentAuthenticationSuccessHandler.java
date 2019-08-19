package com.course.courseselection.authentication;

import com.course.courseselection.command.UserResponse;
import com.course.courseselection.security.JwtUtils;
import com.course.courseselection.translator.StudentTranslator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StudentAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String JSON_API = "application/vnd.api+json";

    @Autowired
    private StudentTranslator studentTranslator;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtUtils.generateToken(userDetails);
        UserResponse userResponse = studentTranslator.translate(token);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(JSON_API);
        response.getWriter().write(objectMapper.writeValueAsString(userResponse));
    }
}
