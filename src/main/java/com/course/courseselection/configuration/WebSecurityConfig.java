package com.course.courseselection.configuration;

import com.course.courseselection.authentication.AdminAuthenticationSuccessHandler;
import com.course.courseselection.authentication.StudentAuthenticationSuccessHandler;
import com.course.courseselection.authentication.UserAuthenticationFailureHandler;
import com.course.courseselection.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Configuration
    @Order(0)
    public static class StudentConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private StudentUserDetailsServiceImp studentService;

        @Autowired
        private JwtStudentAuthenticationTokenFilter jwtStudentAuthenticationTokenFilter;

        @Autowired
        private UserAuthenticationFailureHandler studentAuthenticationFailureHandler;

        @Autowired
        private StudentAuthenticationSuccessHandler studentAuthenticationSuccessHandler;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/student/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/student/login").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.userDetailsService(studentService).formLogin().loginProcessingUrl("/student/login")
                    .successHandler(this.studentAuthenticationSuccessHandler)
                    .failureHandler(this.studentAuthenticationFailureHandler);
            httpSecurity.headers().cacheControl();
            httpSecurity.addFilterBefore(jwtStudentAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(1)
    public static class TeacherConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtStudentAuthenticationTokenFilter jwtStudentAuthenticationTokenFilter;

        @Autowired
        private JwtTeacherAuthenticationTokenFilter jwtTeacherAuthenticationTokenFilter;

        @Autowired
        private TeacherUserDetailsServiceImp teacherService;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/teacher/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/teacher/login").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.userDetailsService(teacherService).formLogin().loginProcessingUrl("/teacher/login");
            httpSecurity.headers().cacheControl();
            httpSecurity.addFilterBefore(jwtTeacherAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }


    @Configuration
    @Order(2)
    public static class AdminConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtAdminAuthenticationTokenFilter jwtAdminAuthenticationTokenFilter;

        @Autowired
        private AdminUserDetailsServiceImp adminService;

        @Autowired
        private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

        @Autowired
        private UserAuthenticationFailureHandler userAuthenticationFailureHandler;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/admin/**")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/admin/login").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.userDetailsService(adminService).formLogin().loginProcessingUrl("/admin/login")
                    .successHandler(adminAuthenticationSuccessHandler)
                    .failureHandler(userAuthenticationFailureHandler);
            httpSecurity.headers().cacheControl();
            httpSecurity.addFilterBefore(jwtAdminAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

//            httpSecurity.exceptionHandling().authenticationEntryPoint()
        }
    }
}
