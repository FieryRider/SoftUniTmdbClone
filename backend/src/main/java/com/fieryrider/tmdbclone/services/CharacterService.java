package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicCharacterDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.CharacterCreateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.CharacterDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.CharacterEntity;

import java.util.List;

public interface CharacterService {
    List<BasicCharacterDto> getAll();
    CharacterDetailsDto getById(String id);
    CharacterEntity getCharacterById(String id);

    EntityIdDto add(CharacterCreateDto characterCreateDto);
    void edit(String id, CharacterCreateDto characterCreateDto);
    void deleteById(String id);
}
