package com.fieryrider.tmdbclone.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Show from;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Show getFrom() {
        return this.from;
    }

    public void setFrom(Show from) {
        this.from = from;
    }

    public Character() {
    }

    public Character(String name, Show from) {
        this.name = name;
        this.from = from;
    }
}
