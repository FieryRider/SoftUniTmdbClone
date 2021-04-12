package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.entities.Character;
import com.fieryrider.tmdbclone.models.entities.Show;

public interface ShowService {
    Show getShow(String id);
    void removeCharacterFromShow(String showId, Character character);
}
