package com.fieryrider.tmdbclone.models.entities.enums;

public enum MovieStatus {
    RELEASED("Released"), IN_PRODUCTION("In Production");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    MovieStatus(String displayName) {
        this.displayName = displayName;
    }
}
