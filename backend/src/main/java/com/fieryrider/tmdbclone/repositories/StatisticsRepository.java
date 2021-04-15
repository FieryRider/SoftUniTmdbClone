package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {
    Optional<StatisticsEntity> findByResponseCode(int responseCode);
}
