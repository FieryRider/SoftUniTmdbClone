package com.fieryrider.tmdbclone.models.dtos.property_dtos;

import java.util.Set;

public class PersonShowDto {
    private String id;
    private String title;
    private int year;
    private Set<CharacterPropertyDto> characters;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<CharacterPropertyDto> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<CharacterPropertyDto> characters) {
        this.characters = characters;
    }

    public PersonShowDto() {
    }
}
