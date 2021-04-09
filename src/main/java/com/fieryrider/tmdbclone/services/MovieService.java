package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.MovieAddDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;

import java.util.List;

public interface MovieService {
    List<BasicMovieDto> getAll();

    MovieDetailsDto getById(String id);

    void deleteById(String id);

    EntityIdDto add(MovieAddDto movieAddDto);
}
