package com.fieryrider.tmdbclone.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfiguration {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationHours;

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return this.tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationHours() {
        return this.tokenExpirationHours;
    }

    public void setTokenExpirationHours(Integer tokenExpirationHours) {
        this.tokenExpirationHours = tokenExpirationHours;
    }

    @Bean
    public SecretKey getSecretKeyHash() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

    public JwtConfiguration() {
    }
}
