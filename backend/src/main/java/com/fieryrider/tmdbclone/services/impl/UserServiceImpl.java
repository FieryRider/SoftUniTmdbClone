package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.dtos.UserRegisterDto;
import com.fieryrider.tmdbclone.models.entities.UserEntity;
import com.fieryrider.tmdbclone.models.entities.UserRoleEntity;
import com.fieryrider.tmdbclone.models.entities.enums.UserRole;
import com.fieryrider.tmdbclone.repositories.UserRepository;
import com.fieryrider.tmdbclone.repositories.UserRoleRepository;
import com.fieryrider.tmdbclone.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void initAdminUser() {
        if (this.userRepository.count() == 0) {
            UserRoleEntity adminUserRoleEntity = new UserRoleEntity(UserRole.ADMIN);
            UserRoleEntity normalUserRoleEntity = new UserRoleEntity(UserRole.NORMAL);
            this.userRoleRepository.saveAll(List.of(adminUserRoleEntity, normalUserRoleEntity));

            UserEntity adminUserEntity = new UserEntity("admin",
                    this.passwordEncoder.encode("1234567890"),
                    "admin@mail.com",
                    Set.of(adminUserRoleEntity));

            this.userRepository.saveAndFlush(adminUserEntity);
        }
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        UserEntity userEntity = this.modelMapper.map(userRegisterDto, UserEntity.class);
        UserRoleEntity userRoleEntity =
                this.userRoleRepository.findByUserRoleEquals(userRegisterDto.getUserRole()).orElseThrow();
        userEntity.setRoles(Set.of(userRoleEntity));
        userEntity.setPassword(this.passwordEncoder.encode(userRegisterDto.getPassword()));
        this.userRepository.saveAndFlush(userEntity);
    }
}
