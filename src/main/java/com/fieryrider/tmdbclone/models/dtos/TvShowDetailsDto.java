package com.fieryrider.tmdbclone.models.dtos;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;

import java.util.Set;

public class TvShowDetailsDto {
    private String id;

    private String title;

    private String overview;

    private int rating;

    private String posterUrl;

    private String officialLanguage;

    private TvShowType type;

    private TvShowStatus status;

    private String network;

    private Set<Genre> genres;

    private Set<MovieCastDto> cast;

    private Set<ShowCrewDto> producers;

    private Set<ShowCrewDto> directors;

    private Set<ShowCrewDto> writers;

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

    public TvShowDetailsDto() {
    }
}
