package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tv_shows")
public class TvShow extends Show {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TvShowType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TvShowStatus status;

    @Column(nullable = false)
    private String network;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tv_shows_creators",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id", referencedColumnName = "id"))
    private Set<Person> creators;

    public TvShowType getType() {
        return this.type;
    }

    public void setType(TvShowType type) {
        this.type = type;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public TvShowStatus getStatus() {
        return this.status;
    }

    public void setStatus(TvShowStatus status) {
        this.status = status;
    }

    public Set<Person> getCreators() {
        return this.creators;
    }

    public void setCreators(Set<Person> creators) {
        this.creators = creators;
    }

    public TvShow() {
    }

    public TvShow(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, TvShowType type, TvShowStatus status, String network) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres);
        this.type = type;
        this.status = status;
        this.network = network;
    }

    public TvShow(String title, String overview, int rating, int releaseYear, String posterUrl, String officialLanguage, Set<Genre> genres, Set<Person> cast, Set<Person> creators, TvShowType type, TvShowStatus status, String network) {
        super(title, overview, rating, releaseYear, posterUrl, officialLanguage, genres, cast);
        this.type = type;
        this.status = status;
        this.network = network;
        this.creators = creators;
    }
}
