package com.fieryrider.tmdbclone.models.dtos;

import com.fieryrider.tmdbclone.models.dtos.property_dtos.PersonKnownForDto;

import java.util.List;

public class BasicPersonDto {
    private String id;

    private String name;

    private String profilePictureUrl;

    private List<PersonKnownForDto> knownFor;

    private boolean popular;

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

    public List<PersonKnownForDto> getKnownFor() {
        return this.knownFor;
    }

    public void setKnownFor(List<PersonKnownForDto> knownFor) {
        this.knownFor = knownFor;
    }

    public boolean isPopular() {
        return this.popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public BasicPersonDto() {
    }
}
