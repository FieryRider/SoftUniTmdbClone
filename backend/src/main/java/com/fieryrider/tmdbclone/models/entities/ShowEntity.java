package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "shows")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ShowEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private int rating;

    private int releaseYear;

    private boolean popular;

    @Column(name = "poster_url", nullable = false)
    private String posterUrl;

    @Column(name = "official_language", nullable = false)
    private String officialLanguage;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "show_id"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "shows_cast",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id", referencedColumnName = "id"))
    private Set<PersonEntity> cast;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "show_character_mapping",
        joinColumns = { @JoinColumn(name = "show_id", referencedColumnName = "id") })
    @MapKeyColumn(name = "actor_name")
    @Column
    private Map<String, String> characters;

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

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public boolean isPopular() {
        return this.popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
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

    public Set<PersonEntity> getCast() {
        return this.cast;
    }

    public void setCast(Set<PersonEntity> cast) {
        this.cast = cast;
    }

    public Map<String, String> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Map<String, String> characters) {
        this.characters = characters;
    }

    public ShowEntity() {
    }

    public ShowEntity(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres) {
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.officialLanguage = officialLanguage;
        this.genres = genres;
        this.popular = false;
    }

    public ShowEntity(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, Set<PersonEntity> cast, Map<String, String> characters) {
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.officialLanguage = officialLanguage;
        this.genres = genres;
        this.cast = cast;
        this.characters = characters;
        this.popular = false;
    }
}
