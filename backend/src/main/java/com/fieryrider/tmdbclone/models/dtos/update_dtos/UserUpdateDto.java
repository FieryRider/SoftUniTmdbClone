package com.fieryrider.tmdbclone.models.dtos.update_dtos;

import com.fieryrider.tmdbclone.models.entities.enums.UserRole;
import com.fieryrider.tmdbclone.validation.EnumNameValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateDto {
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid email")
    private String email;

    @EnumNameValid(enumClass = UserRole.class)
    private String role;

    private String profilePictureUrl;

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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public UserUpdateDto() {
    }
}
