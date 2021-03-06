package com.fieryrider.tmdbclone.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {
    private final JwtConfiguration jwtConfiguration;

    public JwtTokenVerifier(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(this.jwtConfiguration.getAuthorizationHeader());
        if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith(this.jwtConfiguration.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = "";
        try {
            token = authorizationHeader.replace(this.jwtConfiguration.getTokenPrefix(), "");
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(this.jwtConfiguration.getSecretKeyHash()).build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                    authorities
                            .stream()
                            .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException ex) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }
}
