package com.fieryrider.tmdbclone.models.dtos;

import java.util.Set;

public class PersonShowDto {
    private String id;
    private String title;
    private int year;
    private Set<String> characters;

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

    public Set<String> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<String> characters) {
        this.characters = characters;
    }

    public PersonShowDto() {
    }
}
