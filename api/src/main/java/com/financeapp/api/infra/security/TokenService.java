package com.financeapp.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final String issuer = "apifinanceapp";

    public String generateToken(String username) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(username)
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error creating token", exception);
        }
    }

    public String getSubject(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(jwtToken)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token is invalid or expired");
        }
    }

    private Instant getExpirationTime() {
            return Instant.now().plusSeconds(60 * 15); // 15 min
        }
}
