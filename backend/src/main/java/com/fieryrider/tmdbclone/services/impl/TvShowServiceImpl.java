package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.entities.Person;
import com.fieryrider.tmdbclone.models.entities.TvShow;
import com.fieryrider.tmdbclone.repositories.TvShowRepository;
import com.fieryrider.tmdbclone.services.PersonService;
import com.fieryrider.tmdbclone.services.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TvShowServiceImpl implements TvShowService {
    private final TvShowRepository tvShowRepository;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public TvShowServiceImpl(TvShowRepository tvShowRepository, PersonService personService, ModelMapper modelMapper) {
        this.tvShowRepository = tvShowRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BasicTvShowDto> getAll() {
        List<TvShow> tvShows = this.tvShowRepository.findAll();
        List<BasicTvShowDto> basicTvShowDtos = new ArrayList<>();
        for (TvShow tvShow : tvShows)
            basicTvShowDtos.add(this.modelMapper.map(tvShow, BasicTvShowDto.class));

        return basicTvShowDtos;
    }

    @Override
    public TvShowDetailsDto getById(String id) {
        TvShow tvShow;
        try {
            tvShow = this.tvShowRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new NoSuchTvShowException();
        }

        TvShowDetailsDto tvShowDetailsDto = this.modelMapper.map(tvShow, TvShowDetailsDto.class);

        return tvShowDetailsDto;
    }

    @Override
    public void deleteById(String id) {
        this.tvShowRepository.deleteById(id);
    }

    @Override
    public EntityIdDto add(TvShowCreateDto tvShowCreateDto) {
        TvShow tvShow = this.modelMapper.map(tvShowCreateDto, TvShow.class);
        Set<Person> cast = new HashSet<>();
        Set<Person> creators = new HashSet<>();
        for (String creatorId : tvShowCreateDto.getCreators()) {
            try {
                creators.add(this.personService.getPersonById(creatorId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchProducerFound();
            }
        }
        for (String actorId : tvShowCreateDto.getCast()) {
            try {
                cast.add(this.personService.getPersonById(actorId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchCastFound();
            }
        }
        tvShow.setCast(cast);
        tvShow.setCreators(creators);
        TvShow saved = this.tvShowRepository.saveAndFlush(tvShow);
        return new EntityIdDto(saved.getId());
    }
}
