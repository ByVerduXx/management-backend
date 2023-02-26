package com.ofc.management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "d2803688b7d533bbdfb762e4f18148eaab6b7c5d8e26da95cecdc29f6786e518";
    private final static Long ACCESS_TOKEN_EXPIRATION = 2592000L;

    public static String createToken(String name, String lastName, String username, String password) {

        long expiration = ACCESS_TOKEN_EXPIRATION * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        extra.put("lastName", lastName);
        extra.put("password", password);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, ACCESS_TOKEN_SECRET)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String name = (String) claims.get("name");
            String lastName = (String) claims.get("lastName");
            String password = (String) claims.get("password");

            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
