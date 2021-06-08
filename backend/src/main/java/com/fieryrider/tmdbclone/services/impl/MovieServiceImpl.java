package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.EnumDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.MovieEntity;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;
import com.fieryrider.tmdbclone.repositories.MovieRepository;
import com.fieryrider.tmdbclone.services.MovieService;
import com.fieryrider.tmdbclone.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, PersonService personService, ModelMapper modelMapper) {
        super();
        this.movieRepository = movieRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BasicMovieDto> getAll() {
        List<MovieEntity> movies = this.movieRepository.findAll();

        List<BasicMovieDto> basicMovieDtos = new ArrayList<>();
        for (MovieEntity movie : movies) {
            BasicMovieDto basicMovieDto = this.modelMapper.map(movie, BasicMovieDto.class);
            basicMovieDtos.add(basicMovieDto);
        }

        return basicMovieDtos;
    }

    @Override
    public MovieDetailsDto getById(String id) {
        MovieEntity movie;
        try {
            movie = this.movieRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new NoSuchMovieException();
        }

        MovieDetailsDto movieDetailsDto = this.modelMapper.map(movie, MovieDetailsDto.class);
        movieDetailsDto.setStatus(new EnumDto(movie.getStatus().name(), movie.getStatus().getDisplayName()));
        Set<EnumDto> genres = new HashSet<>();
        for (Genre genre : movie.getGenres()) {
            EnumDto enumDto = new EnumDto(genre.name(), genre.getDisplayName());
            genres.add(enumDto);
        }
        movieDetailsDto.setGenres(genres);
        return movieDetailsDto;
    }

    @Override
    public List<BasicMovieDto> getPopular() {
        List<MovieEntity> movies =  this.movieRepository.getAllByPopularEquals(true);
        List<BasicMovieDto> basicMovieDtos = new ArrayList<>();
        for (MovieEntity movie : movies) {
            BasicMovieDto basicMovieDto = this.modelMapper.map(movie, BasicMovieDto.class);
            basicMovieDtos.add(basicMovieDto);
        }

        return basicMovieDtos;
    }

    @Override
    public void setPopular(String id, boolean popular) {
        MovieEntity movie = this.movieRepository.findById(id).orElseThrow();
        movie.setPopular(popular);
        this.movieRepository.saveAndFlush(movie);
    }

    @Override
    @Transactional
    public EntityIdDto add(MovieCreateDto movieCreateDto) {
        MovieEntity movie = this.modelMapper.map(movieCreateDto, MovieEntity.class);
        Set<PersonEntity> cast = new HashSet<>();
        Set<PersonEntity> producers = new HashSet<>();
        Set<PersonEntity> directors = new HashSet<>();
        Set<PersonEntity> writers = new HashSet<>();
        if (movieCreateDto.getProducers() != null) {
            for (String producerId : movieCreateDto.getProducers()) {
                try {
                    PersonEntity producer = this.personService.getPersonById(producerId);
                    producers.add(producer);
                    producer.getProducing().add(movie);
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
        }
        for (String directorId : movieCreateDto.getDirectors()) {
            try {
                PersonEntity director = this.personService.getPersonById(directorId);
                directors.add(director);
                director.getDirecting().add(movie);
            } catch (NoSuchElementException ex) {
                throw new NoSuchDirectorFound();
            }
        }
        for (String writerId : movieCreateDto.getWriters()) {
            try {
                PersonEntity writer = this.personService.getPersonById(writerId);
                writers.add(writer);
                writer.getWriting().add(movie);
            } catch (NoSuchElementException ex) {
                throw new NoSuchWriterFound();
            }
        }
        for (String actorId : movieCreateDto.getCast()) {
            try {
                PersonEntity actor = this.personService.getPersonById(actorId);
                cast.add(actor);
                actor.getActing().add(movie);
            } catch (NoSuchElementException ex) {
                throw new NoSuchCastFound();
            }
        }
        movie.setCast(cast);
        movie.setProducers(producers);
        movie.setDirectors(directors);
        movie.setWriters(writers);
        MovieEntity saved = this.movieRepository.saveAndFlush(movie);
        return new EntityIdDto(saved.getId());
    }

    @Override
    @Transactional
    public void edit(String id, MovieUpdateDto movieUpdateDto) {
        MovieEntity movie = this.movieRepository.findById(id).orElseThrow();

        if (movieUpdateDto.getTitle() != null)
            movie.setTitle(movieUpdateDto.getTitle());
        if (movieUpdateDto.getOverview() != null)
            movie.setOverview(movieUpdateDto.getOverview());
        if (movieUpdateDto.getRating() != null)
            movie.setRating(movieUpdateDto.getRating());
        if (movieUpdateDto.getPosterUrl() != null)
            movie.setPosterUrl(movieUpdateDto.getPosterUrl());
        if (movieUpdateDto.getOfficialLanguage() != null)
            movie.setOfficialLanguage(movieUpdateDto.getOfficialLanguage());
        if (movieUpdateDto.getGenres() != null) {
            Set<Genre> genres = new HashSet<>();
            for (String genre : movieUpdateDto.getGenres())
                genres.add(Genre.valueOf(genre));
            movie.setGenres(genres);
        }
        if (movieUpdateDto.getStatus() != null)
            movie.setStatus(MovieStatus.valueOf(movieUpdateDto.getStatus()));
        if (movieUpdateDto.getBudget() != null)
            movie.setBudget(movieUpdateDto.getBudget());
        if (movieUpdateDto.getRevenue() != null)
            movie.setRevenue(movieUpdateDto.getRevenue());
        if (movieUpdateDto.getReleaseDate() != null)
            movie.setReleaseDate(movieUpdateDto.getReleaseDate());

        if (movieUpdateDto.getProducers() != null) {
            Set<PersonEntity> newProducers = new HashSet<>();
            for (String producerId : movieUpdateDto.getProducers()) {
                try {
                    newProducers.add(this.personService.getPersonById(producerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
            Set<PersonEntity> currentProducers = movie.getProducers();
            for (PersonEntity producer : currentProducers) {
                // XXX: Does it save the person?
                if (!newProducers.contains(producer))
                    producer.getProducing().remove(movie);
            }
            movie.setProducers(newProducers);
            for (PersonEntity producer : newProducers)
                producer.getProducing().add(movie);
        }
        if (movieUpdateDto.getDirectors() != null) {
            Set<PersonEntity> newDirectors = new HashSet<>();
            for (String directorId : movieUpdateDto.getDirectors()) {
                try {
                    newDirectors.add(this.personService.getPersonById(directorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchDirectorFound();
                }
            }
            Set<PersonEntity> currentDirectors = movie.getDirectors();
            for (PersonEntity director : currentDirectors) {
                // XXX: Does it save the person?
                if (!newDirectors.contains(director))
                    director.getDirecting().remove(movie);
            }
            movie.setDirectors(newDirectors);
            for (PersonEntity director : newDirectors)
                director.getDirecting().add(movie);
        }
        if (movieUpdateDto.getWriters() != null) {
            Set<PersonEntity> newWriters = new HashSet<>();
            for (String writerId : movieUpdateDto.getWriters()) {
                try {
                    newWriters.add(this.personService.getPersonById(writerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchWriterFound();
                }
            }
            Set<PersonEntity> currentWriters = movie.getWriters();
            for (PersonEntity writer : currentWriters) {
                // XXX: Does it save the person?
                if (!newWriters.contains(writer))
                    writer.getWriting().remove(movie);
            }
            movie.setWriters(newWriters);
            for (PersonEntity writer : newWriters)
                writer.getWriting().add(movie);
        }
        if (movieUpdateDto.getCast() != null) {
            Set<PersonEntity> newCast = new HashSet<>();
            for (String actorId : movieUpdateDto.getCast()) {
                try {
                    newCast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }
            Set<PersonEntity> currentCast = movie.getCast();
            for (PersonEntity actor : currentCast) {
                // XXX: Does it save the person?
                if (!newCast.contains(actor))
                    actor.getActing().remove(movie);
            }
            movie.setCast(newCast);
            // XXX: Does it save the person?
            for (PersonEntity actor : newCast)
                actor.getActing().add(movie);
        }

        this.movieRepository.saveAndFlush(movie);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        MovieEntity movie = this.movieRepository.findById(id).orElseThrow();
        for (PersonEntity producer : movie.getProducers())
            producer.getProducing().remove(movie);
        for (PersonEntity writer : movie.getWriters())
            writer.getWriting().remove(movie);
        for (PersonEntity director : movie.getDirectors())
            director.getDirecting().remove(movie);
        for (PersonEntity actor : movie.getCast())
            actor.getActing().remove(movie);

        this.movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removePersonFromMovie(String movieId, PersonEntity person) {
        MovieEntity movie = this.movieRepository.findById(movieId).orElseThrow();
        movie.getProducers().remove(person);
        movie.getDirectors().remove(person);
        movie.getWriters().remove(person);
        this.movieRepository.saveAndFlush(movie);
    }
}
