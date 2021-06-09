package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.detail_dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface MovieService {
    List<BasicMovieDto> getAll();
    MovieDetailsDto getById(String id);
    List<BasicMovieDto> getPopular();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void setPopular(String id, boolean popular);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    EntityIdDto add(MovieCreateDto movieCreateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void edit(String id, MovieUpdateDto movieUpdateDto);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(String id);


    void removePersonFromMovie(String movieId, PersonEntity person);
}
