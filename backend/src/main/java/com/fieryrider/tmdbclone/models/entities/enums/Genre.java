package com.fieryrider.tmdbclone.models.entities.enums;

public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SATIRE("Satire"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private final String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    Genre(String name) {
        this.displayName = name;
    }
}
