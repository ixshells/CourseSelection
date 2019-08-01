package com.course.courseselection.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    private Clock clock = DefaultClock.INSTANCE;

    private String generateToken(Map<String, Object> claims, String subject) {

        Date createdDate = clock.now();

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(calculateExpiration(createdDate))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Date calculateExpiration(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    public String getUsernameFromToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return claimsFromToken.getSubject();
    }

    public Date getExpirationFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        JWTUser user = (JWTUser) userDetails;
        return user.getUsername().equals(username)
                && !isTokenExpired(token)
                && !isTokenCreatedBeforePasswordReset(token);
    }

    private boolean isTokenCreatedBeforePasswordReset(String token) {
        return false;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationFromToken(token);
        return expiration.before(clock.now());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>(){{
            put("username", userDetails.getUsername());
        }};
        return generateToken(claims, userDetails.getUsername());
    }
}
