package sn.zone.bakcup_api.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import sn.zone.bakcup_api.data.enums.Role;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "just-a-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(String userId, Role role) {
        return JWT.create()
                .withSubject(userId)
                .withClaim("role", role.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public DecodedJWT verifyAndGetId(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
    }
}
