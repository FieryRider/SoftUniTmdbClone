package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;

import java.util.List;

public interface TvShowService {
    List<BasicTvShowDto> getAll();

    TvShowDetailsDto getById(String id);

    void deleteById(String id);

    EntityIdDto add(TvShowCreateDto tvShowCreateDto);
    void edit(String id, TvShowUpdateDto tvShowUpdateDto);
}
