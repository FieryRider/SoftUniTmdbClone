package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.entities.ShowEntity;
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
    public ShowEntity getShow(String id) {
        return this.showRepository.findById(id).orElseThrow();
    }

}
