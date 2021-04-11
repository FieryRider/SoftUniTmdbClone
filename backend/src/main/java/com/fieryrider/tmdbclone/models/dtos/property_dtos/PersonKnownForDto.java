package com.fieryrider.tmdbclone.models.dtos.property_dtos;

public class PersonKnownForDto {
    private String id;
    private String title;
    private String posterUrl;

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

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public PersonKnownForDto() {
    }
}
