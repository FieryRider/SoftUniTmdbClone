package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.entities.CharacterEntity;
import com.fieryrider.tmdbclone.models.entities.ShowEntity;

public interface ShowService {
    ShowEntity getShow(String id);

    void removeCharacterFromShow(String showId, CharacterEntity character);
}
