package com.fieryrider.tmdbclone.models.dtos.detail_dtos;

import com.fieryrider.tmdbclone.models.entities.enums.UserRole;

import java.util.Set;

public class UserDetailsDto {
    private String username;

    private String password;

    private String email;

    private Set<UserRole> roles;

    private String profilePictureUrl;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public UserDetailsDto() {
    }
}
