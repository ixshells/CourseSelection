package com.course.courseselection.security;

import com.course.courseselection.constant.Role;
import com.course.courseselection.entity.StudentData;
import com.course.courseselection.entity.TeacherData;
import com.course.courseselection.entity.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class JWTUserFactory {
    public static UserDetails create(UserData userData) {
        return new JWTUser(userData.getId(), userData.getName(),
                           userData.getPassword(), mapToGranteAuthority(userData),
                           userData.getPasswordResetTime());
    }

    private static List<GrantedAuthority> mapToGranteAuthority(UserData userData) {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();

        if (userData instanceof StudentData) {
            grantedAuthoritys.add(new SimpleGrantedAuthority(Role.ROLE_STUDENT.toString()));
        }

        if (userData instanceof TeacherData) {
            grantedAuthoritys.add(new SimpleGrantedAuthority(Role.ROLE_TEACHER.toString()));
        }
        return grantedAuthoritys;
    };
}
