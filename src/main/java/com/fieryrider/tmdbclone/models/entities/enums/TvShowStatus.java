package com.fieryrider.tmdbclone.models.entities.enums;

public enum TvShowStatus {
    RETURNING("Returning"),
    CANCELLED("Cancelled"),
    ENDED("Ended");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    TvShowStatus(String displayName) {
        this.displayName = displayName;
    }
}
