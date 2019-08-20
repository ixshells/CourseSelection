package com.course.courseselection.security;

import java.time.LocalDateTime;
import java.util.List;
import com.course.courseselection.constant.Role;
import com.course.courseselection.entity.StudentData;
import com.course.courseselection.entity.TeacherData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactoryTest {

    @Test
    public void should_generate_a_correct_jwt_user_for_student() {
        StudentData studentData = new StudentData();
        studentData.setId("id");
        studentData.setPassword("password");
        studentData.setName("name");
        studentData.setPasswordResetTime(LocalDateTime.now());
        JwtUser jwtUser = (JwtUser) JwtUserFactory.create(studentData);

        Assert.assertEquals(jwtUser.getId(), studentData.getId());
        Assert.assertEquals(jwtUser.getUsername(), studentData.getName());
        Assert.assertEquals(jwtUser.getPassword(), studentData.getPassword());
        Assert.assertEquals(jwtUser.getLastPasswordRestDate(), studentData.getPasswordResetTime());
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) jwtUser.getAuthorities();
        Assert.assertEquals(authorities.get(0).toString(), Role.ROLE_STUDENT.toString());
    }


    @Test
    public void should_generate_a_correct_jwt_user_for_teacher() {
        TeacherData teacherData = new TeacherData();
        teacherData.setId("id");
        teacherData.setPassword("password");
        teacherData.setName("name");
        teacherData.setPasswordResetTime(LocalDateTime.now());
        JwtUser jwtUser = (JwtUser) JwtUserFactory.create(teacherData);

        Assert.assertEquals(jwtUser.getId(), teacherData.getId());
        Assert.assertEquals(jwtUser.getUsername(), teacherData.getName());
        Assert.assertEquals(jwtUser.getPassword(), teacherData.getPassword());
        Assert.assertEquals(jwtUser.getLastPasswordRestDate(), teacherData.getPasswordResetTime());
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) jwtUser.getAuthorities();
        Assert.assertEquals(authorities.get(0).toString(), Role.ROLE_TEACHER.toString());
    }
}