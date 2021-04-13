package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchCharacterFound;
import com.fieryrider.tmdbclone.exceptions.NoSuchPersonException;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.*;
import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;
import com.fieryrider.tmdbclone.repositories.PersonRepository;
import com.fieryrider.tmdbclone.services.CharacterService;
import com.fieryrider.tmdbclone.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final CharacterService characterService;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, CharacterService characterService, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.characterService = characterService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Person getPersonById(String id) {
        return this.personRepository.findById(id).orElseThrow();
    }

    @Override
    public List<BasicPersonDto> getAll() {
        List<Person> people = this.personRepository.findAll();
        List<BasicPersonDto> basicPersonDtos = new ArrayList<>();
        for (Person person : people) {
            BasicPersonDto basicPersonDto = this.modelMapper.map(person, BasicPersonDto.class);
            List<String> knownFor = person.getKnownFor().stream().map(BaseEntity::getId).collect(Collectors.toList());
            basicPersonDto.setKnownFor(knownFor);
            basicPersonDtos.add(basicPersonDto);
        }

        return basicPersonDtos;
    }

    @Override
    public PersonDetailsDto getById(String id) {
        Person person;
        try {
            person = this.personRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new NoSuchPersonException();
        }

        PersonDetailsDto personDetailsDto = this.modelMapper.map(person, PersonDetailsDto.class);
        personDetailsDto.setKnownCredits(person.getActing().size() + person.getDirecting().size() + person.getProducing().size() + person.getWriting().size());

        return personDetailsDto;
    }

    @Override
    public EntityIdDto add(PersonCreateDto personCreateDto) {
        Person person = this.modelMapper.map(personCreateDto, Person.class);
        Person saved = this.personRepository.saveAndFlush(person);
        return new EntityIdDto(saved.getId());
    }

    @Override
    public void edit(String id, PersonUpdateDto personUpdateDto) {
        Person person = this.personRepository.findById(id).orElseThrow();
        if (personUpdateDto.getName() != null)
            person.setName(personUpdateDto.getName());
        if (personUpdateDto.getAge() != null)
            person.setAge(personUpdateDto.getAge());
        if (personUpdateDto.getBiography() != null)
            person.setBiography(personUpdateDto.getBiography());
        if (personUpdateDto.getPlaceOfBirth() != null)
            person.setPlaceOfBirth(personUpdateDto.getPlaceOfBirth());
        if (personUpdateDto.getProfilePictureUrl() != null)
            person.setProfilePictureUrl(personUpdateDto.getProfilePictureUrl());
        if (personUpdateDto.getBirthDate() != null)
            person.setBirthDate(personUpdateDto.getBirthDate());
        if (personUpdateDto.getGender() != null)
            person.setGender(Gender.valueOf(personUpdateDto.getGender()));
        if (personUpdateDto.getMainRole() != null)
            person.setMainRole(PersonRole.valueOf(personUpdateDto.getMainRole()));
        if (personUpdateDto.getPlaying() != null) {
            Set<Character> newPlaying = new HashSet<>();
            for (String characterId : personUpdateDto.getPlaying()) {
                try {
                    newPlaying.add(this.characterService.getById(characterId));
                } catch (NoSuchElementException ex) {
                    throw new NoSuchCharacterFound();
                }
            }

            Set<Character> currentPlaying = person.getPlaying();
            for (Character character : currentPlaying) {
                if (!newPlaying.contains(character))
                    character.getPlayedBy().remove(person);
            }
            currentPlaying.removeIf(character -> !newPlaying.contains(character));
            currentPlaying.addAll(newPlaying);
            for (Character character : currentPlaying)
                character.getPlayedBy().add(person);
        }

        this.personRepository.saveAndFlush(person);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Person person = this.personRepository.findById(id).orElseThrow();

        for (Show show : person.getActing())
            show.getCast().remove(person);
        for (Movie movie : person.getWriting())
            movie.getWriters().remove(person);
        for (Movie movie : person.getProducing())
            movie.getProducers().remove(person);
        for (Movie movie : person.getDirecting())
            movie.getDirectors().remove(person);
        for (TvShow tvShow : person.getCreating())
            tvShow.getCreators().remove(person);
        for (Character character : person.getPlaying())
            character.getPlayedBy().remove(person);

        this.personRepository.delete(person);
    }

    @Override
    @Transactional
    public void removeCharacterFromPerson(String personId, Character character) {
        Person person = this.personRepository.findById(personId).orElseThrow();
        person.getPlaying().remove(character);
        this.personRepository.saveAndFlush(person);
    }
}
