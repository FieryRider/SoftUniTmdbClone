package com.fieryrider.tmdbclone.models.dtos.create_dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CharacterCreateDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterCreateDto() {
    }
}
