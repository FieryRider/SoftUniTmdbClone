package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.PersonAddDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.entities.Person;

import java.util.List;

public interface PersonService {
    List<BasicPersonDto> getAll();
    PersonDetailsDto getById(String id);
    Person getPersonById(String id);

    void deleteById(String id);

    EntityIdDto add(PersonAddDto personAddDto);
}
