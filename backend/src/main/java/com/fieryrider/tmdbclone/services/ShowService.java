package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import com.fieryrider.tmdbclone.models.entities.ShowEntity;

public interface ShowService {
    ShowEntity getShow(String id);

    void removePersonFromShow(String showId, PersonEntity person);
}
