package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchCastFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchProducerFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchTvShowException;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.Person;
import com.fieryrider.tmdbclone.models.entities.TvShow;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowStatus;
import com.fieryrider.tmdbclone.models.entities.enums.TvShowType;
import com.fieryrider.tmdbclone.repositories.TvShowRepository;
import com.fieryrider.tmdbclone.services.PersonService;
import com.fieryrider.tmdbclone.services.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void edit(String id, TvShowUpdateDto tvShowUpdateDto) {
        TvShow tvShow = this.tvShowRepository.findById(id).orElseThrow();

        if (tvShowUpdateDto.getTitle() != null)
            tvShow.setTitle(tvShowUpdateDto.getTitle());
        if (tvShowUpdateDto.getOverview() != null)
            tvShow.setOverview(tvShowUpdateDto.getOverview());
        if (tvShowUpdateDto.getRating() != null)
            tvShow.setRating(tvShowUpdateDto.getRating());
        if (tvShowUpdateDto.getPosterUrl() != null)
            tvShow.setPosterUrl(tvShowUpdateDto.getPosterUrl());
        if (tvShowUpdateDto.getOfficialLanguage() != null)
            tvShow.setOfficialLanguage(tvShowUpdateDto.getOfficialLanguage());

        if (tvShowUpdateDto.getGenres() != null) {
            Set<Genre> genres = new HashSet<>();
            for (String genre : tvShowUpdateDto.getGenres())
                genres.add(Genre.valueOf(genre));
            tvShow.setGenres(genres);
        }

        if (tvShowUpdateDto.getCast() != null) {
            Set<Person> cast = new HashSet<>();
            for (String actorId : tvShowUpdateDto.getCast()) {
                try {
                    cast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }
            tvShow.setCast(cast);
        }
        if (tvShowUpdateDto.getCreators() != null) {
            Set<Person> creators = new HashSet<>();
            for (String creatorId : tvShowUpdateDto.getCreators()) {
                try {
                    creators.add(this.personService.getPersonById(creatorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
            tvShow.setCreators(creators);
        }

        if (tvShowUpdateDto.getType() != null)
            tvShow.setType(TvShowType.valueOf(tvShowUpdateDto.getType()));
        if (tvShowUpdateDto.getStatus() != null)
            tvShow.setStatus(TvShowStatus.valueOf(tvShowUpdateDto.getStatus()));
        if (tvShowUpdateDto.getNetwork() != null)
            tvShow.setNetwork(tvShowUpdateDto.getNetwork());

        this.tvShowRepository.saveAndFlush(tvShow);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TvShow tvShow = this.tvShowRepository.findById(id).orElseThrow();
        for (Person creator : tvShow.getCreators())
            creator.getCreating().remove(tvShow);
        for (Person actor : tvShow.getCast())
            actor.getActing().remove(tvShow);
        for (Character character : tvShow.getCharacters())
            character.getFrom().remove(tvShow);
        this.tvShowRepository.deleteById(id);
    }
}
