package com.fieryrider.tmdbclone.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryrider.tmdbclone.jwt.JwtConfiguration;
import com.fieryrider.tmdbclone.jwt.JwtTokenVerifier;
import com.fieryrider.tmdbclone.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.fieryrider.tmdbclone.services.impl.TmdbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final TmdbUserService userService;
    private final ObjectMapper objectMapper;
    private final JwtConfiguration jwtConfiguration;

    @Autowired
    public ApplicationSecurityConfiguration(TmdbUserService userService, PasswordEncoder passwordEncoder, ObjectMapper objectMapper, JwtConfiguration jwtConfiguration) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final String corsOrigin = "http://localhost:8081";
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new CorsFilter(corsConfigurationSource(corsOrigin)), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), this.jwtConfiguration, this.objectMapper))
                .addFilterAfter(new JwtTokenVerifier(this.jwtConfiguration), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/login", "/users/register").permitAll()
                .antMatchers("/users/profile").authenticated()
                .antMatchers(HttpMethod.GET, "/movies/favourite", "/tv-shows/favourite", "/people/favourite").authenticated()
                .antMatchers(HttpMethod.POST, "/movies/favourite/**", "/tv-shows/favourite/**", "/people/favourite/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/movies/favourite/**", "/tv-shows/favourite/**", "/people/favourite/**").authenticated()
                .antMatchers(HttpMethod.GET, "/movies/popular", "/tv-shows/popular", "/people/popular").permitAll()
                .antMatchers(HttpMethod.POST, "/movies/popular/**", "/tv-shows/popular/**", "/people/popular/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movies/popular/**", "/tv-shows/popular/**", "/people/popular/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movies", "/tv-shows", "/people", "/characters").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movies/**", "/tv-shows/**", "/people/**", "/characters/**", "/genres/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/movies/**", "/tv-shows/**", "/people/**", "/characters/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movies/**", "/tv-shows/**", "/people/**", "/characters/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statistics/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
    private CorsConfigurationSource corsConfigurationSource(String corsOrigin) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList(corsOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS","PUT","PATCH","DELETE"));
        configuration.setMaxAge(10L);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Accept","Access-Control-Request-Method","Access-Control-Request-Headers",
                "Accept-Language","Authorization","Content-Type","Request-Name","Request-Surname","Origin","X-Request-AppVersion",
                "X-Request-OsVersion", "X-Request-Device", "X-Requested-With", "Access-Control-Allow-Headers", "Access-Control-Allow-Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
