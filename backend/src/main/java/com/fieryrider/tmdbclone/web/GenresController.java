package com.fieryrider.tmdbclone.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieryrider.tmdbclone.models.dtos.GenreListDto;
import com.fieryrider.tmdbclone.models.entities.enums.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {
    private final ObjectMapper objectMapper;

    public GenresController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        List<GenreListDto> genreListDtos = new ArrayList<>();
        for (Genre value : Genre.values())
            genreListDtos.add(new GenreListDto(value.name(), value.getDisplayName()));
        JsonNode jsonNode = this.objectMapper.valueToTree(genreListDtos);
        try {
            String retVal = objectMapper.writer().writeValueAsString(jsonNode);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(retVal);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
