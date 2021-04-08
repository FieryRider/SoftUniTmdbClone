package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
