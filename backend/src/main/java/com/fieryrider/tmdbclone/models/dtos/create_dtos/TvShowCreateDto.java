package com.fieryrider.tmdbclone.models.dtos.create_dtos;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;
import com.fieryrider.tmdbclone.validation.EnumNameValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class TvShowCreateDto {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String overview;

    @NotNull(message = "Rating cannot be null")
    private Integer rating;

    @NotNull(message = "PosterURL cannot be null")
    @NotBlank(message = "PosterURL cannot be empty")
    private String posterUrl;

    @NotNull(message = "Official language cannot be null")
    @NotBlank(message = "Official language cannot be empty")
    private String officialLanguage;

    @NotNull(message = "Genres cannot be null")
    @EnumNameValid(enumClass = Genre.class, message = "Genre must be one of the predefined")
    private Set<String> genres;

    @NotNull(message = "Actors cannot be null")
    private Set<String> cast;

    @NotNull(message = "Creators cannot be null")
    private Set<String> creators;

    @NotNull(message = "Type cannot be null")
    @EnumNameValid(enumClass = TvShowType.class, message = "TvShowType must be one of the predefined")
    private String type;

    @NotNull(message = "Status cannot be null")
    @EnumNameValid(enumClass = TvShowStatus.class, message = "Status must be one of the predefined")
    private String status;

    private int releaseYear;

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

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public TvShowCreateDto() {
    }
}
