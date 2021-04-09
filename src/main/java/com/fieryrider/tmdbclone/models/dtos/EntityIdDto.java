package com.fieryrider.tmdbclone.models.dtos;

public class EntityIdDto {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityIdDto() {
    }

    public EntityIdDto(String id) {
        this.id = id;
    }
}
