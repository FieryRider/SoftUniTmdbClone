package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.models.dtos.BasicCharacterDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.CharacterCreateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.CharacterDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.services.CharacterService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/all")
    public List<BasicCharacterDto> getAll() {
        return this.characterService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDetailsDto> get(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.characterService.getById(id));
    }
    @PostMapping
    public ResponseEntity<EntityIdDto> createCharacter(@Valid @RequestBody CharacterCreateDto characterCreateDto) {
        EntityIdDto characterId = this.characterService.add(characterCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(characterId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable String id, @Valid @RequestBody CharacterCreateDto characterCreateDto) {
        try {
            this.characterService.edit(id, characterCreateDto);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        try {
            this.characterService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
