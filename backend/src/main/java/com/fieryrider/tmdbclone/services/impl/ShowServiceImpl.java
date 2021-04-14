package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.entities.CharacterEntity;
import com.fieryrider.tmdbclone.models.entities.ShowEntity;
import com.fieryrider.tmdbclone.repositories.ShowRepository;
import com.fieryrider.tmdbclone.services.ShowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public ShowEntity getShow(String id) {
        return this.showRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void removeCharacterFromShow(String showId, CharacterEntity character) {
        ShowEntity show = this.showRepository.findById(showId).orElseThrow();
        show.getCharacters().remove(character);
        this.showRepository.saveAndFlush(show);
    }
}
