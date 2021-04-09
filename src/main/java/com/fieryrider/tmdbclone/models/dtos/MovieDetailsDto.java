package com.fieryrider.tmdbclone.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class MovieDetailsDto {
    private String id;

    private String title;

    private String overview;

    private int rating;

    private String posterUrl;

    private String officialLanguage;

    private Set<Genre> genres;

    private Set<MovieCastDto> cast;

    private Set<ShowCrewDto> producers;

    private Set<ShowCrewDto> directors;

    private Set<ShowCrewDto> writers;

    private MovieStatus status;

    private BigDecimal budget;

    private BigDecimal revenue;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T00:00:00.000Z'")
    private LocalDate releaseDate;

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

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<MovieCastDto> getCast() {
        return this.cast;
    }

    public void setCast(Set<MovieCastDto> cast) {
        this.cast = cast;
    }

    public Set<ShowCrewDto> getProducers() {
        return this.producers;
    }

    public void setProducers(Set<ShowCrewDto> producers) {
        this.producers = producers;
    }

    public Set<ShowCrewDto> getDirectors() {
        return this.directors;
    }

    public void setDirectors(Set<ShowCrewDto> directors) {
        this.directors = directors;
    }

    public Set<ShowCrewDto> getWriters() {
        return this.writers;
    }

    public void setWriters(Set<ShowCrewDto> writers) {
        this.writers = writers;
    }

    public MovieStatus getStatus() {
        return this.status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public BigDecimal getBudget() {
        return this.budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getRevenue() {
        return this.revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieDetailsDto() {
    }
}
