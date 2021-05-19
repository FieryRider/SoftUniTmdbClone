package com.fieryrider.tmdbclone.web;

import com.fieryrider.tmdbclone.exceptions.NoSuchPersonException;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.services.PersonService;
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
@RequestMapping("/people")
@CrossOrigin
public class PeopleController {
    private final PersonService personService;
    private final UserService userService;

    public PeopleController(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<BasicPersonDto> getAll() {
        return this.personService.getAll();
    }

    @GetMapping("/popular")
    public List<BasicPersonDto> getPopular() {
        return this.personService.getPopular();
    }

    @GetMapping("/favourite")
    public List<BasicPersonDto> getFavourite(Principal principal) {
        return this.userService.getFavouritePeople(principal);
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
    public ResponseEntity<EntityIdDto> createPerson(@Valid @RequestBody PersonCreateDto personCreateDto,
                                               UriComponentsBuilder uriComponentsBuilder) {
        EntityIdDto personId = this.personService.add(personCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(personId);
    }

    @PostMapping("/popular/{id}")
    public ResponseEntity<Void> setPopular(@PathVariable String id) {
        try {
            this.personService.setPopular(id, true);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/favourite/{id}")
    public ResponseEntity<Void> addToFavourite(@PathVariable String id, Principal principal) {
        try {
            this.userService.addFavouritePerson(id, principal);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable String id, @Valid @RequestBody PersonUpdateDto personUpdateDto) {
        try {
            this.personService.edit(id, personUpdateDto);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
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

    @DeleteMapping("/popular/{id}")
    public ResponseEntity<Void> unsetPopular(@PathVariable String id) {
        try {
            this.personService.setPopular(id, false);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
