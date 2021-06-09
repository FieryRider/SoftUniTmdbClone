package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "movies")
public class MovieEntity extends ShowEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieStatus status;
    @Column(nullable = false)
    private BigDecimal budget;
    private BigDecimal revenue;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movies_producers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id", referencedColumnName = "id"))
    private Set<PersonEntity> producers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movies_directors",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
    private Set<PersonEntity> directors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movies_writers",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id"))
    private Set<PersonEntity> writers;


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

    public Set<PersonEntity> getProducers() {
        return this.producers;
    }

    public void setProducers(Set<PersonEntity> producers) {
        this.producers = producers;
    }

    public Set<PersonEntity> getDirectors() {
        return this.directors;
    }

    public void setDirectors(Set<PersonEntity> directors) {
        this.directors = directors;
    }

    public Set<PersonEntity> getWriters() {
        return this.writers;
    }

    public void setWriters(Set<PersonEntity> writers) {
        this.writers = writers;
    }

    public MovieEntity() {
    }

    public MovieEntity(MovieStatus status, BigDecimal budget, LocalDate releaseDate) {
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }

    public MovieEntity(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, MovieStatus status, BigDecimal budget, LocalDate releaseDate) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres);
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }

    public MovieEntity(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, Set<PersonEntity> cast, Set<PersonEntity> producers, Set<PersonEntity> directors, Set<PersonEntity> writers, MovieStatus status, BigDecimal budget, LocalDate releaseDate, Map<String, String> characters) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres, cast, characters);
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
        this.directors = directors;
        this.producers = producers;
        this.writers = writers;
    }
}
