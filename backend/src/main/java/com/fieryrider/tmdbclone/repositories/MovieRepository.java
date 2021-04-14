package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    List<MovieEntity> getAllByPopularEquals(boolean popular);
}
