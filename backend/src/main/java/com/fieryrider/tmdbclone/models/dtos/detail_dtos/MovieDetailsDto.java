package com.fieryrider.tmdbclone.models.dtos.detail_dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.EnumDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.ShowPersonDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class MovieDetailsDto {
    private String id;

    private String title;

    private String overview;

    private int rating;

    private String posterUrl;

    private String officialLanguage;

    private Set<EnumDto> genres;

    private Set<ShowPersonDto> cast;

    private Set<ShowPersonDto> producers;

    private Set<ShowPersonDto> directors;

    private Set<ShowPersonDto> writers;

    private EnumDto status;

    private BigDecimal budget;

    private BigDecimal revenue;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T00:00:00.000Z'")
    private LocalDate releaseDate;

    private Map<String, Set<String>> characters;

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

    public Set<EnumDto> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<EnumDto> genres) {
        this.genres = genres;
    }

    public Set<ShowPersonDto> getCast() {
        return this.cast;
    }

    public void setCast(Set<ShowPersonDto> cast) {
        this.cast = cast;
    }

    public Set<ShowPersonDto> getProducers() {
        return this.producers;
    }

    public void setProducers(Set<ShowPersonDto> producers) {
        this.producers = producers;
    }

    public Set<ShowPersonDto> getDirectors() {
        return this.directors;
    }

    public void setDirectors(Set<ShowPersonDto> directors) {
        this.directors = directors;
    }

    public Set<ShowPersonDto> getWriters() {
        return this.writers;
    }

    public void setWriters(Set<ShowPersonDto> writers) {
        this.writers = writers;
    }

    public EnumDto getStatus() {
        return this.status;
    }

    public void setStatus(EnumDto status) {
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

    public Map<String, Set<String>> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Map<String, Set<String>> characters) {
        this.characters = characters;
    }

    public MovieDetailsDto() {
    }
}
