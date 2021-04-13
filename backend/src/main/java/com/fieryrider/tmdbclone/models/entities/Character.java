package com.fieryrider.tmdbclone.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.EAGER)
    private Set<Show> from;

    @ManyToMany(mappedBy = "playing", fetch = FetchType.EAGER)
    private Set<Person> playedBy;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Show> getFrom() {
        return this.from;
    }

    public void setFrom(Set<Show> from) {
        this.from = from;
    }

    public Set<Person> getPlayedBy() {
        return this.playedBy;
    }

    public void setPlayedBy(Set<Person> playedBy) {
        this.playedBy = playedBy;
    }

    public Character() {
    }

    public Character(String name) {
        this.name = name;
    }
}
