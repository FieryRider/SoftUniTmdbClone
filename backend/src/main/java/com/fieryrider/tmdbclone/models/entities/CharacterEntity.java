package com.fieryrider.tmdbclone.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "characters")
public class CharacterEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.EAGER)
    private Set<ShowEntity> from;

    @ManyToMany(mappedBy = "playing", fetch = FetchType.EAGER)
    private Set<PersonEntity> playedBy;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ShowEntity> getFrom() {
        return this.from;
    }

    public void setFrom(Set<ShowEntity> from) {
        this.from = from;
    }

    public Set<PersonEntity> getPlayedBy() {
        return this.playedBy;
    }

    public void setPlayedBy(Set<PersonEntity> playedBy) {
        this.playedBy = playedBy;
    }

    public CharacterEntity() {
    }

    public CharacterEntity(String name) {
        this.name = name;
    }
}
