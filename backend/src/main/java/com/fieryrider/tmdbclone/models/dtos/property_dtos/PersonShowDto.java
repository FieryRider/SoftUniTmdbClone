package com.fieryrider.tmdbclone.models.dtos.property_dtos;

public class PersonShowDto {
    private String id;
    private String title;
    private int year;

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

    public PersonShowDto() {
    }
}
