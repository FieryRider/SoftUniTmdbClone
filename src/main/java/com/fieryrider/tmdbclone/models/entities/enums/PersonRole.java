package com.fieryrider.tmdbclone.models.entities.enums;

public enum PersonRole {
    ACTING("Acting"),
    PRODUCTION("Production"),
    DIRECTING("Directing"),
    WRITING("Writing");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    PersonRole(String displayName) {
        this.displayName = displayName;
    }
}
