package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(String id);

    List<BasicPersonDto> getAll();
    PersonDetailsDto getById(String id);

    EntityIdDto add(PersonCreateDto personCreateDto);
    void edit(String id, PersonUpdateDto personUpdateDto);
    void deleteById(String id);

    void removeCharacterFromPerson(String personId, Character character);
}
