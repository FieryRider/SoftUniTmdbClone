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
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres, cast, producers, directors, writers);
        this.status = status;
        this.budget = budget;
        this.releaseDate = releaseDate;
    }
}
