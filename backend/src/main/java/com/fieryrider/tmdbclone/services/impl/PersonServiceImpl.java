package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.exceptions.NoSuchPersonException;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.dtos.PersonAddDto;
import com.fieryrider.tmdbclone.models.dtos.PersonDetailsDto;
import com.fieryrider.tmdbclone.models.entities.BaseEntity;
import com.fieryrider.tmdbclone.models.entities.Person;
import com.fieryrider.tmdbclone.repositories.PersonRepository;
import com.fieryrider.tmdbclone.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Person getPersonById(String id) {
        return this.personRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        this.personRepository.deleteById(id);
    }

    @Override
    public EntityIdDto add(PersonAddDto personAddDto) {
        Person person = this.modelMapper.map(personAddDto, Person.class);
        Person saved = this.personRepository.saveAndFlush(person);
        return new EntityIdDto(saved.getId());
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

}
