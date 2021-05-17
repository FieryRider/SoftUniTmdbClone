package com.fieryrider.tmdbclone.models.dtos.property_dtos;

public class EnumDto {
    private String name;
    private String displayName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public EnumDto() {
    }

    public EnumDto(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}
