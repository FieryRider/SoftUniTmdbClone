package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import com.fieryrider.tmdbclone.models.entities.ShowEntity;
import com.fieryrider.tmdbclone.repositories.ShowRepository;
import com.fieryrider.tmdbclone.services.ShowService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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
    public void removePersonFromShow(String showId, PersonEntity person) {
        ShowEntity show = getShow(showId);
        show.getCast().remove(person);

        this.showRepository.saveAndFlush(show);
    }
}
