package com.example.demo.security;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

public class JwtConfig {
    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);


    public static Key getKey() {
        return key;
    }
}
