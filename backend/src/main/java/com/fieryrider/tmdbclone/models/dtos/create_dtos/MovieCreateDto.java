package com.fieryrider.tmdbclone.models.dtos.create_dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;
import com.fieryrider.tmdbclone.validation.EnumNameValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class MovieCreateDto {
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
    @EnumNameValid(enumClass = Genre.class, message = "Genre must be one of the predefined")
    private Set<String> genres;

    @NotNull(message = "Actors cannot be null")
    private Set<String> cast;

    @NotNull(message = "Producers cannot be null")
    private Set<String> producers;

    @NotNull(message = "Directors cannot be null")
    private Set<String> directors;

    @NotNull(message = "Writers cannot be null")
    private Set<String> writers;

    @NotNull(message = "You must specify status")
    @EnumNameValid(enumClass = MovieStatus.class, message = "Status must be one of the predefined")
    private String status;

    @NotNull(message = "You must specify budget")
    @PositiveOrZero(message = "Budget cannot be negative")
    private BigDecimal budget;

    private BigDecimal revenue;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "You must specify release date")
    private LocalDate releaseDate;

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
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

    public MovieCreateDto() {
    }
}
