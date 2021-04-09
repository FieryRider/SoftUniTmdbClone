package com.fieryrider.tmdbclone.models.dtos;

public class GenreListDto {
    private String name;
    private String displayName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public GenreListDto() {
    }

    public GenreListDto(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}
