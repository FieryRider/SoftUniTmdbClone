package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchPersonException;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.PersonCreateDto;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.PersonUpdateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.*;
import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;
import com.fieryrider.tmdbclone.repositories.PersonRepository;
import com.fieryrider.tmdbclone.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PersonEntity getPersonById(String id) {
        return this.personRepository.findById(id).orElseThrow();
    }

    @Override
    public List<BasicPersonDto> getAll() {
        List<PersonEntity> people = this.personRepository.findAll();
        List<BasicPersonDto> basicPersonDtos = new ArrayList<>();
        for (PersonEntity person : people) {
            BasicPersonDto basicPersonDto = this.modelMapper.map(person, BasicPersonDto.class);
            List<String> knownFor = person.getKnownFor().stream().map(BaseEntity::getId).collect(Collectors.toList());
            basicPersonDto.setKnownFor(knownFor);
            basicPersonDtos.add(basicPersonDto);
        }

        return basicPersonDtos;
    }

    @Override
    public PersonDetailsDto getById(String id) {
        PersonEntity person;
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
    public List<BasicPersonDto> getPopular() {
        List<PersonEntity> people = this.personRepository.getAllByPopularEquals(true);
        List<BasicPersonDto> basicPersonDtos = new ArrayList<>();
        for (PersonEntity person : people) {
            BasicPersonDto basicPersonDto = this.modelMapper.map(person, BasicPersonDto.class);
            basicPersonDtos.add(basicPersonDto);
        }

        return basicPersonDtos;
    }

    @Override
    public void setPopular(String id, boolean popular) {
        PersonEntity person = this.personRepository.findById(id).orElseThrow();
        person.setPopular(true);
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public EntityIdDto add(PersonCreateDto personCreateDto) {
        PersonEntity person = this.modelMapper.map(personCreateDto, PersonEntity.class);
        PersonEntity saved = this.personRepository.saveAndFlush(person);
        return new EntityIdDto(saved.getId());
    }

    @Override
    @Transactional
    public void edit(String id, PersonUpdateDto personUpdateDto) {
        PersonEntity person = this.personRepository.findById(id).orElseThrow();
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

        this.personRepository.saveAndFlush(person);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PersonEntity person = this.personRepository.findById(id).orElseThrow();

        for (ShowEntity show : person.getActing())
            show.getCast().remove(person);
        for (MovieEntity movie : person.getWriting())
            movie.getWriters().remove(person);
        for (MovieEntity movie : person.getProducing())
            movie.getProducers().remove(person);
        for (MovieEntity movie : person.getDirecting())
            movie.getDirectors().remove(person);
        for (TvShowEntity tvShow : person.getCreating())
            tvShow.getCreators().remove(person);

        this.personRepository.delete(person);
    }
}
