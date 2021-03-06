package com.fieryrider.tmdbclone.models.dtos;

import java.util.List;

public class BasicPersonDto {
    private String id;

    private String name;

    private List<String> knownFor;

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

    public List<String> getKnownFor() {
        return this.knownFor;
    }

    public void setKnownFor(List<String> knownFor) {
        this.knownFor = knownFor;
    }

    public BasicPersonDto() {
    }
}
