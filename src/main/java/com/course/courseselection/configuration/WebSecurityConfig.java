package com.course.courseselection.configuration;

import com.course.courseselection.security.JWTTeacherAuthenticationTokenFilter;
import com.course.courseselection.security.JwtAuthenticationTokenFilter;
import com.course.courseselection.security.StudentUserDetailsServiceImp;
import com.course.courseselection.security.TeacherUserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private JWTTeacherAuthenticationTokenFilter jwtTeacherAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    public JWTTeacherAuthenticationTokenFilter teacherAuthenticationTokenFilter() throws Exception {
//        return new JWTTeacherAuthenticationTokenFilter();
//    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/authentication/**").permitAll()
                .antMatchers("/student/login").permitAll()
                .antMatchers("/teacher/login").permitAll()
                .anyRequest().authenticated();

        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtTeacherAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Configuration
    @Order(0)
    public static class StudentConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private StudentUserDetailsServiceImp studentService;

        @Autowired
        private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

        @Autowired
        private JWTTeacherAuthenticationTokenFilter jwtTeacherAuthenticationTokenFilter;

        @Autowired
        private StudentAuthenticationFailureHandler studentAuthenticationFailureHandler;

        @Autowired
        private StudentAuthenticationSuccessHandler studentAuthenticationSuccessHandler;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(
                            HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    .antMatchers("/authentication/**").permitAll()
                    .antMatchers("/student/login").permitAll()
                    .antMatchers("/teacher/login").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.userDetailsService(studentService).formLogin().loginProcessingUrl("/student/login")
                    .successHandler(this.studentAuthenticationSuccessHandler)
                    .failureHandler(this.studentAuthenticationFailureHandler);
            httpSecurity.headers().cacheControl();
            httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
            httpSecurity.addFilterBefore(jwtTeacherAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(1)
    public static class TeacherConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

        @Autowired
        private JWTTeacherAuthenticationTokenFilter jwtTeacherAuthenticationTokenFilter;

        @Autowired
        private TeacherUserDetailsServiceImp teacherService;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(
                            HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    .antMatchers("/authentication/**").permitAll()
                    .antMatchers("/student/login").permitAll()
                    .antMatchers("/teacher/login").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.userDetailsService(teacherService).formLogin().loginProcessingUrl("/teacher/login");
            httpSecurity.headers().cacheControl();
            httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
            httpSecurity.addFilterBefore(jwtTeacherAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
