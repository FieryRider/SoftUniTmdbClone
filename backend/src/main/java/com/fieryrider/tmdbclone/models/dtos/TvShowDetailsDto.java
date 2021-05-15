package com.fieryrider.tmdbclone.models.dtos;

import com.fieryrider.tmdbclone.models.dtos.property_dtos.CharacterPropertyDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.ShowPersonDto;
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

    private int releaseYear;

    private String network;

    private Set<Genre> genres;

    private Set<ShowPersonDto> cast;

    private Set<ShowPersonDto> creators;

    private Set<CharacterPropertyDto> characters;

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

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<ShowPersonDto> getCast() {
        return this.cast;
    }

    public void setCast(Set<ShowPersonDto> cast) {
        this.cast = cast;
    }

    public Set<ShowPersonDto> getCreators() {
        return this.creators;
    }

    public void setCreators(Set<ShowPersonDto> creators) {
        this.creators = creators;
    }

    public Set<CharacterPropertyDto> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<CharacterPropertyDto> characters) {
        this.characters = characters;
    }

    public TvShowDetailsDto() {
    }
}
