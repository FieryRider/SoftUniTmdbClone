package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.NoSuchPersonException;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.PersonAddDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.services.PersonService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<BasicPersonDto> getAll() {
        return this.personService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailsDto> getPerson(@PathVariable String id) {
        try {
            PersonDetailsDto person = this.personService.getById(id);
            return ResponseEntity.ok(person);
        } catch (NoSuchPersonException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntityIdDto> createPerson(@Valid @RequestBody PersonAddDto personAddDto,
                                               UriComponentsBuilder uriComponentsBuilder) {
        EntityIdDto personId = this.personService.add(personAddDto);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(personId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        try {
            this.personService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
