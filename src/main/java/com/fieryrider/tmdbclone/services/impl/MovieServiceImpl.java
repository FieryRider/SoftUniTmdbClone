package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.*;
import com.fieryrider.tmdbclone.models.entities.Movie;
import com.fieryrider.tmdbclone.models.entities.Person;
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
    public EntityIdDto add(MovieAddDto movieAddDto) {
        Movie movie = this.modelMapper.map(movieAddDto, Movie.class);
        Set<Person> cast = new HashSet<>();
        Set<Person> producers = new HashSet<>();
        Set<Person> directors = new HashSet<>();
        Set<Person> writers = new HashSet<>();
        for (String producerId : movieAddDto.getProducers()) {
            try {
                producers.add(this.personService.getPersonById(producerId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchProducerFound();
            }
        }
        for (String directorId : movieAddDto.getDirectors()) {
            try {
                directors.add(this.personService.getPersonById(directorId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchDirectorFound();
            }
        }
        for (String writerId : movieAddDto.getWriters()) {
            try {
                writers.add(this.personService.getPersonById(writerId));
            } catch (NoSuchElementException ex) {
                throw new NoSuchWriterFound();
            }
        }
        for (String actorId : movieAddDto.getCast()) {
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
}
