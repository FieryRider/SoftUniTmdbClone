package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchCastFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchCharacterFound;
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
import com.fieryrider.tmdbclone.services.CharacterService;
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
    private final CharacterService characterService;
    private final ModelMapper modelMapper;

    public TvShowServiceImpl(TvShowRepository tvShowRepository, PersonService personService, CharacterService characterService, ModelMapper modelMapper) {
        this.tvShowRepository = tvShowRepository;
        this.personService = personService;
        this.characterService = characterService;
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
    public List<BasicTvShowDto> getPopular() {
        List<TvShow> tvShows = this.tvShowRepository.getAllByPopularEquals(true);
        List<BasicTvShowDto> basicTvShowDtos = new ArrayList<>();
        for (TvShow tvShow : tvShows) {
            BasicTvShowDto basicTvShowDto = this.modelMapper.map(tvShow, BasicTvShowDto.class);
            basicTvShowDtos.add(basicTvShowDto);
        }

        return basicTvShowDtos;
    }

    @Override
    public void setPopular(String id, boolean popular) {
        TvShow tvShow = this.tvShowRepository.findById(id).orElseThrow();
        tvShow.setPopular(popular);
        this.tvShowRepository.saveAndFlush(tvShow);
    }

    @Override
    @Transactional
    public EntityIdDto add(TvShowCreateDto tvShowCreateDto) {
        TvShow tvShow = this.modelMapper.map(tvShowCreateDto, TvShow.class);
        Set<Person> cast = new HashSet<>();
        Set<Person> creators = new HashSet<>();
        for (String creatorId : tvShowCreateDto.getCreators()) {
            try {
                Person creator = this.personService.getPersonById(creatorId);
                creators.add(creator);
                creator.getCreating().add(tvShow);
            } catch (NoSuchElementException ex) {
                throw new NoSuchProducerFound();
            }
        }
        for (String actorId : tvShowCreateDto.getCast()) {
            try {
                Person actor = this.personService.getPersonById(actorId);
                cast.add(actor);
                actor.getActing().add(tvShow);
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
    @Transactional
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
            Set<Person> newCast = new HashSet<>();
            for (String actorId : tvShowUpdateDto.getCast()) {
                try {
                    newCast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }

            Set<Person> currentCast = tvShow.getCast();
            for (Person actor : currentCast) {
                if (!newCast.contains(actor))
                    actor.getActing().remove(tvShow);
            }
            currentCast.removeIf(actor -> !newCast.contains(actor));
            currentCast.addAll(newCast);
            for (Person actor : currentCast)
                actor.getActing().add(tvShow);
        }
        if (tvShowUpdateDto.getCreators() != null) {
            Set<Person> newCreators = new HashSet<>();
            for (String creatorId : tvShowUpdateDto.getCreators()) {
                try {
                    newCreators.add(this.personService.getPersonById(creatorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }

            Set<Person> currentCreators = tvShow.getCreators();
            for (Person creator : currentCreators) {
                if (!newCreators.contains(creator))
                    creator.getCreating().remove(tvShow);
            }
            currentCreators.removeIf(creator -> !newCreators.contains(creator));
            currentCreators.addAll(newCreators);
            for (Person creator : currentCreators)
                creator.getCreating().add(tvShow);
        }
        if (tvShowUpdateDto.getCharacters() !=  null) {
            Set<Character> newCharacters = new HashSet<>();
            for (String characterId : tvShowUpdateDto.getCharacters()) {
                try {
                    newCharacters.add(this.characterService.getCharacterById(characterId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCharacterFound();
                }
            }

            Set<Character> currentCharacters = tvShow.getCharacters();
            for (Character character : currentCharacters) {
                if (!newCharacters.contains(character))
                    character.getFrom().remove(tvShow);
            }
            currentCharacters.removeIf(character -> !newCharacters.contains(character));
            currentCharacters.addAll(newCharacters);
            for (Character character : currentCharacters)
                character.getFrom().add(tvShow);
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
