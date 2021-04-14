package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, String> {
}
