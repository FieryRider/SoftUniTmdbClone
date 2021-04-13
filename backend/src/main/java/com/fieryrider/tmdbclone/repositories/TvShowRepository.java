package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TvShow, String> {
    List<TvShow> getAllByPopularEquals(boolean popular);
}
