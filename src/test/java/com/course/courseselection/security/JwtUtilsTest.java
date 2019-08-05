package com.course.courseselection.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class JwtUtilsTest {

    // name ssh, password 12345678
    private static final String VALID_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzc2giLCJpYXQiOjE1NjQ5MDcwODgsImV4cCI6NDY3NTMwNzA4OH0.mv2I0GS9f_3wah7Tr7-qc-YKMyzgZGkPIesCgvaRkEU";

    private JwtUtils jwtUtils = new JwtUtils();

    @Before
    public void setUp() throws Exception {
        setField(jwtUtils, "expiration", 86400L);
        setField(jwtUtils, "secret", "CourseSelectionSecret");
    }

    @Test
    public void should_get_username_from_token() {
        String username = jwtUtils.getUsernameFromToken(VALID_TOKEN);
        Assert.assertEquals(username, "ssh");
    }

    @Test
    public void should_generate_to_correct_token() {
        JwtUser userDetails = buildJwtUser();
        String token = jwtUtils.generateToken(userDetails);
        Assert.assertNotNull(token);
    }

    @Test
    public void should_validate_as_correct_token() {
        Boolean isValid = jwtUtils.validateToken(VALID_TOKEN, buildJwtUser());
        Assert.assertTrue(isValid);
    }

    private JwtUser buildJwtUser() {
        return new JwtUser("id", "ssh", "12345678", new ArrayList<>(), LocalDateTime.now());
    }

}