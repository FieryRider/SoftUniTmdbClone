package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;

import java.util.List;

public interface PersonService {
    PersonEntity getPersonById(String id);

    List<BasicPersonDto> getAll();
    PersonDetailsDto getById(String id);
    List<BasicPersonDto> getPopular();
    void setPopular(String id, boolean popular);

    EntityIdDto add(PersonCreateDto personCreateDto);
    void edit(String id, PersonUpdateDto personUpdateDto);
    void deleteById(String id);
}
