package com.example.demo.service.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import com.example.demo.security.JwtConfig;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class JwtService {

    private static final Logger logger = Logger.getLogger(JwtService.class.getName());
    private final Key key = JwtConfig.getKey();
    private static final long EXPIRATION_TIME = 86400000L; // 1 dia

    public String generateToken(String username, String role) {
        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
        logger.info("Token generated: " + token);
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.warning("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
