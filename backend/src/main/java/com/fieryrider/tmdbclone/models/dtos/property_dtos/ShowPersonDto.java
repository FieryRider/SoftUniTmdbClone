package com.fieryrider.tmdbclone.models.dtos.property_dtos;

import java.util.Set;

public class ShowPersonDto {
    private String id;
    private String name;
    private String profilePictureUrl;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public ShowPersonDto() {
    }
}
