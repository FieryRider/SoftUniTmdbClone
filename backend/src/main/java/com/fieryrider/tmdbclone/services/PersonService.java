package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.detail_dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PersonService {
    PersonEntity getPersonById(String id);

    List<BasicPersonDto> getAll();
    PersonDetailsDto getById(String id);
    List<BasicPersonDto> getPopular();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void setPopular(String id, boolean popular);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    EntityIdDto add(PersonCreateDto personCreateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void edit(String id, PersonUpdateDto personUpdateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(String id);
}
