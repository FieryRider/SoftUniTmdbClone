package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, String> {
}
