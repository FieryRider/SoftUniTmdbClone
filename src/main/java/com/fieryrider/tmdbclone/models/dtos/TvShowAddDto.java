package com.fieryrider.tmdbclone.models.dtos;

import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class TvShowAddDto {
    @NotNull(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String overview;

    @NotNull(message = "Rating cannot be empty")
    private int rating;

    @NotNull(message = "PosterURL cannot be empty")
    @NotBlank(message = "PosterURL cannot be empty")
    private String posterUrl;

    @NotNull(message = "Official language cannot be empty")
    @NotBlank(message = "Official language cannot be empty")
    private String officialLanguage;

    @NotNull(message = "Genres cannot be null")
    private Set<String> genres;

    @NotNull(message = "Actors cannot be null")
    private Set<String> cast;

    @NotNull(message = "Producers cannot be null")
    private Set<String> producers;

    @NotNull(message = "Directors cannot be null")
    private Set<String> directors;

    @NotNull(message = "Writers cannot be null")
    private Set<String> writers;

    @NotNull(message = "Type cannot be null")
    private TvShowType type;

    @NotNull(message = "Status cannot be null")
    private TvShowStatus status;

    @NotNull(message = "Network cannot be null")
    @NotBlank(message = "Network cannot be empty")
    private String network;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getOfficialLanguage() {
        return this.officialLanguage;
    }

    public void setOfficialLanguage(String officialLanguage) {
        this.officialLanguage = officialLanguage;
    }

    public Set<String> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Set<String> getCast() {
        return this.cast;
    }

    public void setCast(Set<String> cast) {
        this.cast = cast;
    }

    public Set<String> getProducers() {
        return this.producers;
    }

    public void setProducers(Set<String> producers) {
        this.producers = producers;
    }

    public Set<String> getDirectors() {
        return this.directors;
    }

    public void setDirectors(Set<String> directors) {
        this.directors = directors;
    }

    public Set<String> getWriters() {
        return this.writers;
    }

    public void setWriters(Set<String> writers) {
        this.writers = writers;
    }

    public TvShowType getType() {
        return this.type;
    }

    public void setType(TvShowType type) {
        this.type = type;
    }

    public TvShowStatus getStatus() {
        return this.status;
    }

    public void setStatus(TvShowStatus status) {
        this.status = status;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public TvShowAddDto() {
    }
}
