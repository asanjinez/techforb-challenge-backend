package com.techforb.challenge.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${spring.jwt.expiration-time}")
    private Long TIEMPO_EXPIRACION;

    @Value("${spring.jwt.secret-key}")
    private String SECRETO;
    public String generateToken(Authentication authentication) {
        String username =  authentication.getClass().getName();
        Date tiempoActual = new Date();
        Date tiempoExpiracion = new Date(tiempoActual.getTime() + TIEMPO_EXPIRACION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(tiempoExpiracion)
                .signWith(SignatureAlgorithm.HS256,SECRETO)
                .compact();
        return token;
    }

    public String obtenerUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRETO)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRETO).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Token Invalido");
        }
    }
}
