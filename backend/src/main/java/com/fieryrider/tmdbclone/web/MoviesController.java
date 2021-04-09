package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.MovieAddDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.services.MovieService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<BasicMovieDto> getAll() {
        return this.movieService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDto> getMovie(@PathVariable String id) {
        try {
            MovieDetailsDto movie = this.movieService.getById(id);
            return ResponseEntity.ok(movie);
        } catch (NoSuchMovieException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntityIdDto> createMovie(@Valid @RequestBody MovieAddDto movieAddDto,
                                              UriComponentsBuilder uriComponentsBuilder) {
        try {
            EntityIdDto movieId = this.movieService.add(movieAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(movieId);
        } catch (NoSuchCastFound | NoSuchDirectorFound | NoSuchProducerFound | NoSuchWriterFound ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        try {
            this.movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
