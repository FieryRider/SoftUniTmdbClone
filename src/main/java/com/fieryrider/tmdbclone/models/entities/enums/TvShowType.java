package com.fieryrider.tmdbclone.models.entities.enums;

public enum TvShowType {
    SCRIPTED("Scripted"),
    DOCUMENTARY("Documentary");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    TvShowType(String displayName) {
        this.displayName = displayName;
    }
}
