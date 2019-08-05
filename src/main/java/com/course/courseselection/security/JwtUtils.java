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
public class JwtUtils {
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    private Clock clock = DefaultClock.INSTANCE;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

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

    private Date getExpirationFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetails;
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
}
