package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.MovieDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.MovieCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.MovieUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.services.MovieService;
import com.fieryrider.tmdbclone.services.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    private final MovieService movieService;
    private final UserService userService;

    public MoviesController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<BasicMovieDto> getAll() {
        return this.movieService.getAll();
    }

    @GetMapping("/popular")
    public List<BasicMovieDto> getPopular() {
        return this.movieService.getPopular();
    }

    @GetMapping("/favourite")
    public List<BasicMovieDto> getFavourite(Principal principal) {
        return this.userService.getFavouriteMovies(principal);
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
    public ResponseEntity<EntityIdDto> createMovie(@Valid @RequestBody MovieCreateDto movieCreateDto,
                                              UriComponentsBuilder uriComponentsBuilder) {
        try {
            EntityIdDto movieId = this.movieService.add(movieCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(movieId);
        } catch (NoSuchCastFound | NoSuchDirectorFound | NoSuchProducerFound | NoSuchWriterFound ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/popular/{id}")
    public ResponseEntity<Void> setPopular(@PathVariable String id) {
        try {
            this.movieService.setPopular(id, true);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/favourite/{id}")
    public ResponseEntity<Void> addToFavourite(@PathVariable String id, Principal principal) {
        try {
            this.userService.addFavouriteMovie(id, principal);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable String id, @Valid @RequestBody MovieUpdateDto movieUpdateDto) {
        try {
            this.movieService.edit(id, movieUpdateDto);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
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

    @DeleteMapping("/popular/{id}")
    public ResponseEntity<Void> unsetPopular(@PathVariable String id) {
        try {
            this.movieService.setPopular(id, false);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
