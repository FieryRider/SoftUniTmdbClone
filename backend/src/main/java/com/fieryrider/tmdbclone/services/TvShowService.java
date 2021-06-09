package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.detail_dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TvShowService {
    List<BasicTvShowDto> getAll();
    TvShowDetailsDto getById(String id);
    List<BasicTvShowDto> getPopular();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void setPopular(String id, boolean popular);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    EntityIdDto add(TvShowCreateDto tvShowCreateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void edit(String id, TvShowUpdateDto tvShowUpdateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(String id);


    void removePersonFromTvShow(String tvShowId, PersonEntity person);
}
