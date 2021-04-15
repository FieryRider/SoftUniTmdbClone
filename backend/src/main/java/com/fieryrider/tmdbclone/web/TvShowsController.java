package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.TvShowUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.services.TvShowService;
import com.fieryrider.tmdbclone.services.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tv-shows")
public class TvShowsController {
    private final TvShowService tvShowService;
    private final UserService userService;

    public TvShowsController(TvShowService tvShowService, UserService userService) {
        this.tvShowService = tvShowService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<BasicTvShowDto> getAll() {
        return this.tvShowService.getAll();
    }

    @GetMapping("/popular")
    public List<BasicTvShowDto> getPopular() {
        return this.tvShowService.getPopular();
    }

    @GetMapping("/favourite")
    public List<BasicTvShowDto> getFavourite(Principal principal) {
        return this.userService.getFavouriteTvShows(principal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShowDetailsDto> getTvShow(@PathVariable String id) {
        try {
            TvShowDetailsDto tvShow = this.tvShowService.getById(id);
            return ResponseEntity.ok(tvShow);
        } catch (NoSuchTvShowException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntityIdDto> createTvShow(@Valid @RequestBody TvShowCreateDto tvShowCreateDto) {
        try {
            EntityIdDto tvShowId = this.tvShowService.add(tvShowCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(tvShowId);
        } catch (NoSuchCastFound | NoSuchDirectorFound | NoSuchProducerFound | NoSuchWriterFound ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/popular/{id}")
    public ResponseEntity<Void> setPopular(@PathVariable String id) {
        try {
            this.tvShowService.setPopular(id, true);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/favourite/{id}")
    public ResponseEntity<Void> addToFavourite(@PathVariable String id, Principal principal) {
        try {
            this.userService.addFavouriteTvShow(id, principal);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTvShow(@PathVariable String id,
                                             @Valid @RequestBody TvShowUpdateDto tvShowUpdateDto) {
        try {
            this.tvShowService.edit(id, tvShowUpdateDto);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvShow(@PathVariable String id) {
        try {
            this.tvShowService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/popular/{id}")
    public ResponseEntity<Void> unsetPopular(@PathVariable String id) {
        try {
            this.tvShowService.setPopular(id, false);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
