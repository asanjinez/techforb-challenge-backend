package com.techforb.challenge.security;

import com.techforb.challenge.exceptions.JwtAuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    private String obtenerToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring(7, bearer.length());

        log.error("Se intento obtener un token sin el prefijo Bearer: " + bearer);
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = obtenerToken(request);

        if (StringUtils.hasText(token)) {
            try {
                if (this.validarToken(token)) {
                    String username = tokenProvider.obtenerUsername(token);
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

                filterChain.doFilter(request, response);
            } catch (ExpiredJwtException e) {
                handlerExceptionResolver.resolveException(request, response, null, new JwtAuthException("Sesión caducada, por favor inicie sesión nuevamente"));

            } catch (JwtException e) {
                handlerExceptionResolver.resolveException(request, response, null, new JwtAuthException("Token inválido o no soportado"));
            }
        }


    }
    public boolean validarToken(String token){
            Jwts.parser().setSigningKey(tokenProvider.getSecreto()).build().parseClaimsJws(token);
            return true;
    }
}
