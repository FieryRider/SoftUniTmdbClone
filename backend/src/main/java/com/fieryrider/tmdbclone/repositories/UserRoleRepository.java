package com.fieryrider.tmdbclone.repositories;

import com.fieryrider.tmdbclone.models.entities.UserRoleEntity;
import com.fieryrider.tmdbclone.models.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    Optional<UserRoleEntity> findByUserRoleEquals(UserRole userRole);
}
