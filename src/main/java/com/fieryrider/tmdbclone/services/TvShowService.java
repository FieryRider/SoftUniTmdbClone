package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowAddDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;

import java.util.List;

public interface TvShowService {
    List<BasicTvShowDto> getAll();

    TvShowDetailsDto getById(String id);

    void deleteById(String id);

    EntityIdDto add(TvShowAddDto tvShowAddDto);
}
