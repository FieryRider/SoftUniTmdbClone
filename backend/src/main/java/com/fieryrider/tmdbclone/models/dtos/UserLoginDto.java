package com.fieryrider.tmdbclone.models.dtos;

public class UserLoginDto {
    String username;
    String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDto() {
    }
}
