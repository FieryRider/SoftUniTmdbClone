package com.fieryrider.tmdbclone.models.dtos.utility_dtos;

import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.BasicShowDto;

import java.util.Set;

public class CharacterDetailsDto {
    private String id;
    private String name;

    private Set<BasicShowDto> from;
    private Set<BasicPersonDto> playedBy;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BasicShowDto> getFrom() {
        return this.from;
    }

    public void setFrom(Set<BasicShowDto> from) {
        this.from = from;
    }

    public Set<BasicPersonDto> getPlayedBy() {
        return this.playedBy;
    }

    public void setPlayedBy(Set<BasicPersonDto> playedBy) {
        this.playedBy = playedBy;
    }

    public CharacterDetailsDto() {
    }
}
