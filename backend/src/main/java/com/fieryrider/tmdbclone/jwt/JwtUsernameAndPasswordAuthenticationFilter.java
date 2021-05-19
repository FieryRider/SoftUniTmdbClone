package com.fieryrider.tmdbclone.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfiguration jwtConfiguration, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtConfiguration = jwtConfiguration;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest =
                    this.objectMapper.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(usernameAndPasswordAuthenticationRequest.getUsername(),
                            usernameAndPasswordAuthenticationRequest.getPassword());

            Authentication authenticate = this.authenticationManager.authenticate(authentication);
            return authenticate;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusHours(this.jwtConfiguration.getTokenExpirationHours())))
                .signWith(this.jwtConfiguration.getSecretKeyHash())
                .compact();

        List<String> roles = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        for (String role : roles) {
            System.out.println(role);
        }
        request.setAttribute("token", this.jwtConfiguration.getTokenPrefix() + token);
        request.setAttribute("roles", roles);
        response.addHeader(this.jwtConfiguration.getAuthorizationHeader(), this.jwtConfiguration.getTokenPrefix() + token);
        chain.doFilter(request, response);
    }
}
