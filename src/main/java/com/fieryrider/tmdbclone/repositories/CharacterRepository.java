package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {
}
