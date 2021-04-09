package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shows")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Show extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private int rating;

    private int releaseYear;

    @Column(name = "poster_url", nullable = false)
    private String posterUrl;

    @Column(name = "official_language", nullable = false)
    private String officialLanguage;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "show_id"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shows_cast",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id", referencedColumnName = "id"))
    private Set<Person> cast;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shows_producers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id", referencedColumnName = "id"))
    private Set<Person> producers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shows_directors",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
    private Set<Person> directors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shows_writers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id"))
    private Set<Person> writers;

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

    public Set<Person> getCast() {
        return this.cast;
    }

    public void setCast(Set<Person> cast) {
        this.cast = cast;
    }

    public Set<Person> getProducers() {
        return this.producers;
    }

    public void setProducers(Set<Person> producers) {
        this.producers = producers;
    }

    public Set<Person> getDirectors() {
        return this.directors;
    }

    public void setDirectors(Set<Person> directors) {
        this.directors = directors;
    }

    public Set<Person> getWriters() {
        return this.writers;
    }

    public void setWriters(Set<Person> writers) {
        this.writers = writers;
    }

    public Show() {
    }

    public Show(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres) {
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.officialLanguage = officialLanguage;
        this.genres = genres;
    }

    public Show(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, Set<Person> cast, Set<Person> producers, Set<Person> directors, Set<Person> writers) {
        this.title = title;
        this.overview = overview;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.officialLanguage = officialLanguage;
        this.genres = genres;
        this.cast = cast;
        this.producers = producers;
        this.directors = directors;
        this.writers = writers;
    }
}
