package com.fieryrider.tmdbclone.configs;

import com.fieryrider.tmdbclone.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
    }
    /*
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().
                antMatchers(HttpMethod.POST, "/users/login", "/users/register").permitAll().
                antMatchers(HttpMethod.GET, "/movies", "/tv-shows", "/people", "/characters").permitAll().
                antMatchers("/users/profile", "/movies/favourite", "/tv-shows/favourite", "/people/favourite").authenticated().
                antMatchers(HttpMethod.POST, "/movies", "/tv-shows", "/people", "/characters").hasRole("ADMIN").
                antMatchers(HttpMethod.PUT, "/movies", "/tv-shows", "/people", "/characters").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/movies/**", "/tv-shows/**", "/people/**", "/characters/**").hasRole("ADMIN").
                antMatchers(HttpMethod.PUT, "/movies/**", "/tv-shows/**", "/people/**", "/characters/**").hasRole("ADMIN").
                anyRequest().authenticated();
    }
     */
}
