package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchCastFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchProducerFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchTvShowException;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.EnumDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import com.fieryrider.tmdbclone.models.entities.TvShowEntity;
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
        List<TvShowEntity> tvShows = this.tvShowRepository.findAll();
        List<BasicTvShowDto> basicTvShowDtos = new ArrayList<>();
        for (TvShowEntity tvShow : tvShows)
            basicTvShowDtos.add(this.modelMapper.map(tvShow, BasicTvShowDto.class));

        return basicTvShowDtos;
    }

    @Override
    public TvShowDetailsDto getById(String id) {
        TvShowEntity tvShow;
        try {
            tvShow = this.tvShowRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new NoSuchTvShowException();
        }

        TvShowDetailsDto tvShowDetailsDto = this.modelMapper.map(tvShow, TvShowDetailsDto.class);
        Set<EnumDto> genres = new HashSet<>();
        for (Genre genre : tvShow.getGenres()) {
            EnumDto enumDto = new EnumDto(genre.name(), genre.getDisplayName());
            genres.add(enumDto);
        }
        tvShowDetailsDto.setGenres(genres);

        return tvShowDetailsDto;
    }

    @Override
    public List<BasicTvShowDto> getPopular() {
        List<TvShowEntity> tvShows = this.tvShowRepository.getAllByPopularEquals(true);
        List<BasicTvShowDto> basicTvShowDtos = new ArrayList<>();
        for (TvShowEntity tvShow : tvShows) {
            BasicTvShowDto basicTvShowDto = this.modelMapper.map(tvShow, BasicTvShowDto.class);
            basicTvShowDtos.add(basicTvShowDto);
        }

        return basicTvShowDtos;
    }

    @Override
    public void setPopular(String id, boolean popular) {
        TvShowEntity tvShow = this.tvShowRepository.findById(id).orElseThrow();
        tvShow.setPopular(popular);
        this.tvShowRepository.saveAndFlush(tvShow);
    }

    @Override
    @Transactional
    public EntityIdDto add(TvShowCreateDto tvShowCreateDto) {
        TvShowEntity tvShow = this.modelMapper.map(tvShowCreateDto, TvShowEntity.class);
        Set<PersonEntity> cast = new HashSet<>();
        Set<PersonEntity> creators = new HashSet<>();
        for (String creatorId : tvShowCreateDto.getCreators()) {
            try {
                PersonEntity creator = this.personService.getPersonById(creatorId);
                creators.add(creator);
                creator.getCreating().add(tvShow);
            } catch (NoSuchElementException ex) {
                throw new NoSuchProducerFound();
            }
        }
        for (String actorId : tvShowCreateDto.getCast()) {
            try {
                PersonEntity actor = this.personService.getPersonById(actorId);
                cast.add(actor);
                actor.getActing().add(tvShow);
            } catch (NoSuchElementException ex) {
                throw new NoSuchCastFound();
            }
        }
        tvShow.setCast(cast);
        tvShow.setCreators(creators);
        TvShowEntity saved = this.tvShowRepository.saveAndFlush(tvShow);
        return new EntityIdDto(saved.getId());
    }

    @Override
    @Transactional
    public void edit(String id, TvShowUpdateDto tvShowUpdateDto) {
        TvShowEntity tvShow = this.tvShowRepository.findById(id).orElseThrow();

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
            Set<PersonEntity> newCast = new HashSet<>();
            for (String actorId : tvShowUpdateDto.getCast()) {
                try {
                    newCast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }

            Set<PersonEntity> currentCast = tvShow.getCast();
            for (PersonEntity actor : currentCast) {
                if (!newCast.contains(actor))
                    actor.getActing().remove(tvShow);
            }
            currentCast.removeIf(actor -> !newCast.contains(actor));
            currentCast.addAll(newCast);
            for (PersonEntity actor : currentCast)
                actor.getActing().add(tvShow);
        }
        if (tvShowUpdateDto.getCreators() != null) {
            Set<PersonEntity> newCreators = new HashSet<>();
            for (String creatorId : tvShowUpdateDto.getCreators()) {
                try {
                    newCreators.add(this.personService.getPersonById(creatorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }

            Set<PersonEntity> currentCreators = tvShow.getCreators();
            for (PersonEntity creator : currentCreators) {
                if (!newCreators.contains(creator))
                    creator.getCreating().remove(tvShow);
            }
            currentCreators.removeIf(creator -> !newCreators.contains(creator));
            currentCreators.addAll(newCreators);
            for (PersonEntity creator : currentCreators)
                creator.getCreating().add(tvShow);
        }

        if (tvShowUpdateDto.getType() != null)
            tvShow.setType(TvShowType.valueOf(tvShowUpdateDto.getType()));
        if (tvShowUpdateDto.getStatus() != null)
            tvShow.setStatus(TvShowStatus.valueOf(tvShowUpdateDto.getStatus()));
        if (tvShowUpdateDto.getReleaseYear() != null)
            tvShow.setReleaseYear(tvShowUpdateDto.getReleaseYear());
        if (tvShowUpdateDto.getNetwork() != null)
            tvShow.setNetwork(tvShowUpdateDto.getNetwork());

        this.tvShowRepository.saveAndFlush(tvShow);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TvShowEntity tvShow = this.tvShowRepository.findById(id).orElseThrow();
        for (PersonEntity creator : tvShow.getCreators())
            creator.getCreating().remove(tvShow);
        for (PersonEntity actor : tvShow.getCast())
            actor.getActing().remove(tvShow);

        this.tvShowRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removePersonFromTvShow(String tvShowId, PersonEntity person) {
        TvShowEntity tvShow = this.tvShowRepository.findById(tvShowId).orElseThrow();
        tvShow.getCreators().remove(person);
        this.tvShowRepository.saveAndFlush(tvShow);
    }
}
