package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.*;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.TvShowCreateDto;
import com.fieryrider.tmdbclone.models.dtos.TvShowDetailsDto;
import com.fieryrider.tmdbclone.services.TvShowService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tv-shows")
public class TvShowsController {
    private final TvShowService tvShowService;

    public TvShowsController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping("/all")
    public List<BasicTvShowDto> getAll() {
        return this.tvShowService.getAll();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTvShow(@PathVariable String id) {
        try {
            this.tvShowService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
