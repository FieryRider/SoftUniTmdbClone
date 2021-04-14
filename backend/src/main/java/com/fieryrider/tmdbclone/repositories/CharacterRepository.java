package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, String> {
}
