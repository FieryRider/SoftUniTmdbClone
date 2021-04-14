package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.dtos.BasicCharacterDto;
import com.fieryrider.tmdbclone.models.dtos.create_dtos.CharacterCreateDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.CharacterDetailsDto;
import com.fieryrider.tmdbclone.models.dtos.utility_dtos.EntityIdDto;
import com.fieryrider.tmdbclone.models.entities.CharacterEntity;
import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import com.fieryrider.tmdbclone.models.entities.ShowEntity;
import com.fieryrider.tmdbclone.repositories.CharacterRepository;
import com.fieryrider.tmdbclone.services.CharacterService;
import com.fieryrider.tmdbclone.services.PersonService;
import com.fieryrider.tmdbclone.services.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private PersonService personService;
    private final ShowService showService;
    private final ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepository characterRepository, ShowService showService, ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.showService = showService;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public List<BasicCharacterDto> getAll() {
        List<CharacterEntity> characters = this.characterRepository.findAll();

        List<BasicCharacterDto> basicCharacterDtos = new ArrayList<>();
        for (CharacterEntity character : characters) {
            BasicCharacterDto basicCharacterDto = this.modelMapper.map(character, BasicCharacterDto.class);
            basicCharacterDtos.add(basicCharacterDto);
        }

        return basicCharacterDtos;
    }

    @Override
    public CharacterDetailsDto getById(String id) {
        CharacterEntity character = this.characterRepository.findById(id).orElseThrow();
        return this.modelMapper.map(character, CharacterDetailsDto.class);
    }

    @Override
    public CharacterEntity getCharacterById(String id) {
        return this.characterRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public EntityIdDto add(CharacterCreateDto characterCreateDto) {
        CharacterEntity character = new CharacterEntity(characterCreateDto.getName());
        CharacterEntity saved = this.characterRepository.saveAndFlush(character);
        return new EntityIdDto(saved.getId());
    }

    @Override
    @Transactional
    public void edit(String id, CharacterCreateDto characterCreateDto) {
        CharacterEntity character = this.characterRepository.findById(id).orElseThrow();
        character.setName(characterCreateDto.getName());
        this.characterRepository.saveAndFlush(character);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CharacterEntity character = getCharacterById(id);
        for (PersonEntity person : character.getPlayedBy())
            this.personService.removeCharacterFromPerson(person.getId(), character);
        for (ShowEntity show : character.getFrom())
            this.showService.removeCharacterFromShow(show.getId(), character);

        this.characterRepository.deleteById(id);
    }
}
