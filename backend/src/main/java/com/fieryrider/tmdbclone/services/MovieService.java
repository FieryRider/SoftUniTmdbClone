package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;

import java.util.List;

public interface MovieService {
    List<BasicMovieDto> getAll();
    MovieDetailsDto getById(String id);

    EntityIdDto add(MovieCreateDto movieCreateDto);
    void edit(String id, MovieUpdateDto movieUpdateDto);
    void deleteById(String id);
}
