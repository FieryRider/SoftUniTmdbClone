package com.fieryrider.tmdbclone.models.entities.enums;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    Gender(String displayName) {
        this.displayName = displayName;
    }
}
