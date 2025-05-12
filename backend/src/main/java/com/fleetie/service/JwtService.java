package com.fleetie.service;

import com.fleetie.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getUsername());
        claims.put("companyId", user.getCompany().getId());
        claims.put("roles", user.getAuthorities());
        return createToken(claims, user.getUsername());
    }

    private Date extractExpiration(String token) {
        return new Date();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        //todo refactor so we don't get the user here, this is DOSable

        Date expirationDate = extractExpiration(token);
        if (expirationDate.before(new Date())) {
            return false;
        }
        return !expirationDate.before(new Date());
    }

    public String extractUsername(String jwtToken) {
        return Jwts.parser()
            .verifyWith(getJwtSigningKey())
            .build()
            .parseSignedClaims(jwtToken)
            .getPayload()
            .getSubject();
    }

    private SecretKey getJwtSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    private String createToken(Map<String, Object> claims, String username) {
        long expiration = 3600000; //TODO
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getJwtSigningKey())
                .compact();
    }

    //TODO
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}

