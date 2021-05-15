package com.fieryrider.tmdbclone.models.dtos;

import java.time.LocalDate;

public class BasicTvShowDto {
    private String id;

    private String title;

    private int rating;

    private String posterUrl;

    private LocalDate releaseDate;

    private boolean popular;

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

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isPopular() {
        return this.popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public BasicTvShowDto() {
    }
}
