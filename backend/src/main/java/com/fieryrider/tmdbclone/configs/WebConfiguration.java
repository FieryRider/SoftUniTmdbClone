package com.fieryrider.tmdbclone.configs;

import com.fieryrider.tmdbclone.interceptors.CreateMovieInterceptor;
import com.fieryrider.tmdbclone.interceptors.ResponseStatusInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    private final CreateMovieInterceptor createMovieInterceptor;
    private final ResponseStatusInterceptor responseStatusInterceptor;

    public WebConfiguration(CreateMovieInterceptor createMovieInterceptor, ResponseStatusInterceptor responseStatusInterceptor) {
        this.createMovieInterceptor = createMovieInterceptor;
        this.responseStatusInterceptor = responseStatusInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.createMovieInterceptor);
        registry.addInterceptor(this.responseStatusInterceptor);
    }
}
