package com.course.courseselection.security;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailsServiceImpTest {
    @InjectMocks
    private JwtUserDetailsServiceImp jwtUserDetailsService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void should_load_user_details() {
        StudentData studentData = new StudentData();
        studentData.setName("name");
        studentData.setPassword("password");
        studentData.setPasswordResetTime(LocalDateTime.now());
        studentData.setId("id");
        when(studentRepository.findByName(anyString())).thenReturn(studentData);

        JwtUser userDetails = (JwtUser) jwtUserDetailsService.loadUserByUsername("username");

        Assert.assertEquals(userDetails.getId(), studentData.getId());
        Assert.assertEquals(userDetails.getUsername(), studentData.getName());
        Assert.assertEquals(userDetails.getPassword(), studentData.getPassword());
        Assert.assertEquals(userDetails.getLastPasswordRestDate(), studentData.getPasswordResetTime());
    }


    @Test(expected = UsernameNotFoundException.class)
    public void should_throw_username_not_found_exception_when_load_user_is_null() {
        when(studentRepository.findByName(anyString())).thenReturn(null);
        jwtUserDetailsService.loadUserByUsername("username");
    }
}