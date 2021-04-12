package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie extends Show {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieStatus status;
    @Column(nullable = false)
    private BigDecimal budget;
    private BigDecimal revenue;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies_producers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id", referencedColumnName = "id"))
    private Set<Person> producers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies_directors",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
    private Set<Person> directors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies_writers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id"))
    private Set<Person> writers;


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

    public Movie() {
    }

    public Movie(MovieStatus status, BigDecimal budget, LocalDate releaseDate) {
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, MovieStatus status, BigDecimal budget, LocalDate releaseDate) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres);
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, Set<Person> cast, Set<Person> producers, Set<Person> directors, Set<Person> writers, MovieStatus status, BigDecimal budget, LocalDate releaseDate) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres, cast);
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
        this.directors = directors;
        this.producers = producers;
        this.writers = writers;
    }
}
