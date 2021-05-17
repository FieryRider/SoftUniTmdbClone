package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;

import java.util.List;

public interface TvShowService {
    List<BasicTvShowDto> getAll();
    TvShowDetailsDto getById(String id);
    List<BasicTvShowDto> getPopular();
    void setPopular(String id, boolean popular);

    EntityIdDto add(TvShowCreateDto tvShowCreateDto);
    void edit(String id, TvShowUpdateDto tvShowUpdateDto);
    void deleteById(String id);


    void removePersonFromTvShow(String tvShowId, PersonEntity person);
}
