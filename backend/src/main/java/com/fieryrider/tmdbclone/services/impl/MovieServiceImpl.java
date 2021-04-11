package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.*;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.Movie;
import com.fieryrider.tmdbclone.models.entities.Person;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import com.fieryrider.tmdbclone.models.entities.enums.MovieStatus;
import com.fieryrider.tmdbclone.repositories.MovieRepository;
import com.fieryrider.tmdbclone.services.MovieService;
import com.fieryrider.tmdbclone.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private PersonService personService;
    private ModelMapper modelMapper;

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
    public void deleteById(String id) {
        this.movieRepository.deleteById(id);
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
                    producers.add(this.personService.getPersonById(producerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
        }
        for (String directorId : movieCreateDto.getDirectors()) {
            try {
                directors.add(this.personService.getPersonById(directorId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchDirectorFound();
            }
        }
        for (String writerId : movieCreateDto.getWriters()) {
            try {
                writers.add(this.personService.getPersonById(writerId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchWriterFound();
            }
        }
        for (String actorId : movieCreateDto.getCast()) {
            try {
                cast.add(this.personService.getPersonById(actorId));
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
            Set<Person> producers = new HashSet<>();
            for (String producerId : movieUpdateDto.getProducers()) {
                try {
                    producers.add(this.personService.getPersonById(producerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchProducerFound();
                }
            }
            movie.setProducers(producers);
        }
        if (movieUpdateDto.getDirectors() != null) {
            Set<Person> directors = new HashSet<>();
            for (String directorId : movieUpdateDto.getDirectors()) {
                try {
                    directors.add(this.personService.getPersonById(directorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchDirectorFound();
                }
            }
            movie.setDirectors(directors);
        }
        if (movieUpdateDto.getWriters() != null) {
            Set<Person> writers = new HashSet<>();
            for (String writerId : movieUpdateDto.getWriters()) {
                try {
                    writers.add(this.personService.getPersonById(writerId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchWriterFound();
                }
            }
            movie.setWriters(writers);
        }
        if (movieUpdateDto.getCast() != null) {
            Set<Person> cast = new HashSet<>();
            for (String actorId : movieUpdateDto.getCast()) {
                try {
                    cast.add(this.personService.getPersonById(actorId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCastFound();
                }
            }
            movie.setCast(cast);
        }

        this.movieRepository.saveAndFlush(movie);
    }
}
