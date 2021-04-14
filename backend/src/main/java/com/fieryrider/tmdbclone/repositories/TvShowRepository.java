package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.TvShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TvShowEntity, String> {
    List<TvShowEntity> getAllByPopularEquals(boolean popular);
}
