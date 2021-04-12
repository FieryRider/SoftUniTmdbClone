package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.Show;
import com.fieryrider.tmdbclone.repositories.ShowRepository;
import com.fieryrider.tmdbclone.services.ShowService;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show getShow(String id) {
        return this.showRepository.findById(id).orElseThrow();
    }

    @Override
    public void removeCharacterFromShow(String showId, Character character) {
        Show show = this.showRepository.findById(showId).orElseThrow();
        show.getCharacters().remove(character);
    }
}
