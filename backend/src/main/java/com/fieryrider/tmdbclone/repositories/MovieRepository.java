package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> getAllByPopularEquals(boolean popular);
}
