package com.fieryrider.tmdbclone.models.dtos.utility_dtos;

import java.util.List;

public class TokenDto {
    private String token;
    private List<String> roles;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public TokenDto(String token, List<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public TokenDto() {
    }
}
