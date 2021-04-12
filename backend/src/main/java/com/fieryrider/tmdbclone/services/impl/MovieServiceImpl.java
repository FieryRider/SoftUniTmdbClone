package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.Movie;
import com.fieryrider.tmdbclone.models.entities.Person;
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
        this.movieRepository = movieRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BasicMovieDto> getAll() {
        List<Movie> movies = this.movieRepository.findAll();

        List<BasicMovieDto> basicMovieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            BasicMovieDto basicMovieDto = this.modelMapper.map(movie, BasicMovieDto.class);
            basicMovieDtos.add(basicMovieDto);
        }

        return basicMovieDtos;
    }

    @Override
    public MovieDetailsDto getById(String id) {
        Movie movie;
        try {
            movie = this.movieRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new NoSuchMovieException();
        }

        MovieDetailsDto movieDetailsDto = this.modelMapper.map(movie, MovieDetailsDto.class);
        return movieDetailsDto;
    }

    @Override
    public EntityIdDto add(MovieCreateDto movieCreateDto) {
        Movie movie = this.modelMapper.map(movieCreateDto, Movie.class);
        Set<Person> cast = new HashSet<>();
        Set<Person> producers = new HashSet<>();
        Set<Person> directors = new HashSet<>();
        Set<Person> writers = new HashSet<>();
        if (movieCreateDto.getProducers() != null) {
            for (String producerId : movieCreateDto.getProducers()) {
                try {
                    Person producer = this.personService.getPersonById(producerId);
                    producers.add(producer);
                    producer.getProducing().add(movie);
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
        }
        for (String directorId : movieCreateDto.getDirectors()) {
            try {
                Person director = this.personService.getPersonById(directorId);
                directors.add(director);
                director.getDirecting().add(movie);
            } catch (NoSuchElementException ex) {
                throw new NoSuchDirectorFound();
            }
        }
        for (String writerId : movieCreateDto.getWriters()) {
            try {
                Person writer = this.personService.getPersonById(writerId);
                writers.add(writer);
                writer.getWriting().add(movie);
            } catch (NoSuchElementException ex) {
                throw new NoSuchWriterFound();
            }
        }
        for (String actorId : movieCreateDto.getCast()) {
            try {
                Person actor = this.personService.getPersonById(actorId);
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
        Movie saved = this.movieRepository.saveAndFlush(movie);
        return new EntityIdDto(saved.getId());
    }

    @Override
    @Transactional
    public void edit(String id, MovieUpdateDto movieUpdateDto) {
        Movie movie = this.movieRepository.findById(id).orElseThrow();

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
            Set<Person> newProducers = new HashSet<>();
            for (String producerId : movieUpdateDto.getProducers()) {
                try {
                    newProducers.add(this.personService.getPersonById(producerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
            Set<Person> currentProducers = movie.getProducers();
            for (Person producer : currentProducers) {
                if (!newProducers.contains(producer))
                    producer.getProducing().remove(movie);
            }
            currentProducers.removeIf(producer -> !newProducers.contains(producer));
            currentProducers.addAll(newProducers);
            for (Person producer : currentProducers)
                producer.getProducing().add(movie);
        }
        if (movieUpdateDto.getDirectors() != null) {
            Set<Person> newDirectors = new HashSet<>();
            for (String directorId : movieUpdateDto.getDirectors()) {
                try {
                    newDirectors.add(this.personService.getPersonById(directorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchDirectorFound();
                }
            }
            Set<Person> currentDirectors = movie.getDirectors();
            for (Person director : currentDirectors) {
                if (!newDirectors.contains(director))
                    director.getDirecting().remove(movie);
            }
            currentDirectors.removeIf(director -> !newDirectors.contains(director));
            currentDirectors.addAll(newDirectors);
            for (Person director : currentDirectors)
                director.getDirecting().add(movie);
        }
        if (movieUpdateDto.getWriters() != null) {
            Set<Person> newWriters = new HashSet<>();
            for (String writerId : movieUpdateDto.getWriters()) {
                try {
                    newWriters.add(this.personService.getPersonById(writerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchWriterFound();
                }
            }
            Set<Person> currentWriters = movie.getWriters();
            for (Person writer : currentWriters) {
                if (!newWriters.contains(writer))
                    writer.getWriting().remove(movie);
            }
            currentWriters.removeIf(writer -> !newWriters.contains(writer));
            currentWriters.addAll(newWriters);
            for (Person writer : currentWriters)
                writer.getWriting().add(movie);
        }
        if (movieUpdateDto.getCast() != null) {
            Set<Person> newCast = new HashSet<>();
            for (String actorId : movieUpdateDto.getCast()) {
                try {
                    newCast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }
            Set<Person> currentCast = movie.getCast();
            for (Person actor : currentCast) {
                if (!newCast.contains(actor))
                    actor.getActing().remove(movie);
            }
            currentCast.removeIf(actor -> !newCast.contains(actor));
            currentCast.addAll(newCast);
            for (Person actor : currentCast)
                actor.getActing().add(movie);
        }

        this.movieRepository.saveAndFlush(movie);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow();
        for (Person producer : movie.getProducers())
            producer.getProducing().remove(movie);
        for (Person writer : movie.getWriters())
            writer.getWriting().remove(movie);
        for (Person director : movie.getDirectors())
            director.getDirecting().remove(movie);
        for (Person actor : movie.getCast())
            actor.getActing().remove(movie);
        for (Character character : movie.getCharacters())
            character.getFrom().remove(movie);

        this.movieRepository.deleteById(id);
    }
}
