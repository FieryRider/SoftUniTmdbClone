package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    List<PersonEntity> getAllByPopularEquals(boolean popular);
}
