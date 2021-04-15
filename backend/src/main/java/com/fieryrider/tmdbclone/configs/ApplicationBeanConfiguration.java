package com.fieryrider.tmdbclone.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryrider.tmdbclone.interceptors.CreateMovieInterceptor;
import com.fieryrider.tmdbclone.interceptors.ResponseStatusInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public CreateMovieInterceptor createMovieInterceptor() {
        return new CreateMovieInterceptor();
    }
    @Bean
    ResponseStatusInterceptor responseStatusInterceptor() {
        return new ResponseStatusInterceptor();
    }
}
