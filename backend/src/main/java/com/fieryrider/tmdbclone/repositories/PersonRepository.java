package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> getAllByPopularEquals(boolean popular);
}
