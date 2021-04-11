package com.fieryrider.tmdbclone.models.dtos.update_dtos;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;
import com.fieryrider.tmdbclone.validation.EnumNameValid;
import com.fieryrider.tmdbclone.validation.NullOrNotBlank;

import java.util.Set;

public class TvShowUpdateDto {
    @NullOrNotBlank(message = "Title cannot be empty")
    private String title;

    private String overview;

    private Integer rating;

    @NullOrNotBlank(message = "PosterURL cannot be empty")
    private String posterUrl;

    @NullOrNotBlank(message = "Official language cannot be empty")
    private String officialLanguage;

    @EnumNameValid(enumClass = Genre.class, message = "Genre must be one of the predefined")
    private Set<String> genres;

    private Set<String> cast;

    private Set<String> creators;

    @EnumNameValid(enumClass = TvShowType.class, message = "TvShowType must be one of the predefined")
    private String type;

    @EnumNameValid(enumClass = TvShowStatus.class, message = "Status must be one of the predefined")
    private String status;

    @NullOrNotBlank(message = "Network cannot be empty")
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

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
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

    public Set<String> getCreators() {
        return this.creators;
    }

    public void setCreators(Set<String> creators) {
        this.creators = creators;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public TvShowUpdateDto() {
    }
}
