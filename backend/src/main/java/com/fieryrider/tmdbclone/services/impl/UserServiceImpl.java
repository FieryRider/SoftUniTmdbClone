package com.fieryrider.tmdbclone.services.impl;

import com.fieryrider.tmdbclone.models.dtos.*;
import com.fieryrider.tmdbclone.models.dtos.update_dtos.UserUpdateDto;
import com.fieryrider.tmdbclone.models.entities.*;
import com.fieryrider.tmdbclone.models.entities.enums.UserRole;
import com.fieryrider.tmdbclone.repositories.UserRepository;
import com.fieryrider.tmdbclone.repositories.UserRoleRepository;
import com.fieryrider.tmdbclone.services.PersonService;
import com.fieryrider.tmdbclone.services.ShowService;
import com.fieryrider.tmdbclone.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ShowService showService;
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ShowService showService, PersonService personService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.showService = showService;
        this.personService = personService;
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
                    Set.of(adminUserRoleEntity, normalUserRoleEntity));

            this.userRepository.saveAndFlush(adminUserEntity);
        }
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        UserEntity userEntity = this.modelMapper.map(userRegisterDto, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userRegisterDto.getPassword()));

        UserRoleEntity adminUserRoleEntity = this.userRoleRepository.findByUserRoleEquals(UserRole.ADMIN).orElseThrow();
        Set<UserRoleEntity> userRoleEntities = new HashSet<>(Set.of(
                this.userRoleRepository.findByUserRoleEquals(UserRole.valueOf(userRegisterDto.getUserRole())).orElseThrow()));
        if (userRoleEntities.contains(adminUserRoleEntity))
            userRoleEntities.add(this.userRoleRepository.findByUserRoleEquals(UserRole.NORMAL).orElseThrow());
        userEntity.setRoles(userRoleEntities);

        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserDetailsDto getUserDetails(Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        TypeMap<UserEntity, UserDetailsDto> userEntityUserDetailsDtoTypeMap = this.modelMapper.createTypeMap(UserEntity.class, UserDetailsDto.class).addMappings(
                m -> {
                    m.map(UserEntity::getUsername, UserDetailsDto::setUsername);
                    m.map(UserEntity::getPassword, UserDetailsDto::setPassword);
                    m.map(UserEntity::getEmail, UserDetailsDto::setEmail);
                    m.map(s -> s.getRoles().stream().map(UserRoleEntity::getUserRole).collect(Collectors.toSet()), UserDetailsDto::setRoles);
                }
        );
        return userEntityUserDetailsDtoTypeMap.map(userEntity);
    }

    @Override
    public void editUser(UserUpdateDto userUpdateDto, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        userEntity.setPassword(this.passwordEncoder.encode(userUpdateDto.getPassword()));
        userEntity.setEmail(userUpdateDto.getEmail());
        userEntity.setProfilePictureUrl(userUpdateDto.getProfilePictureUrl());

        UserRole newUserRole = UserRole.valueOf(userUpdateDto.getRole());
        UserRoleEntity newUserRoleEntity = this.userRoleRepository.findByUserRoleEquals(newUserRole).orElseThrow();
        userEntity.setRoles(new HashSet<>(Set.of(newUserRoleEntity)));

        UserRoleEntity adminUserRoleEntity = this.userRoleRepository.findByUserRoleEquals(UserRole.ADMIN).orElseThrow();
        if (newUserRoleEntity.equals(adminUserRoleEntity))
            userEntity.getRoles().add(this.userRoleRepository.findByUserRoleEquals(UserRole.NORMAL).orElseThrow());

        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<BasicMovieDto> getFavouriteMovies(Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        List<BasicMovieDto> basicMovieDtos = new ArrayList<>();
        for (MovieEntity favouriteMovie : userEntity.getFavouriteMovies()) {
            BasicMovieDto basicMovieDto = this.modelMapper.map(favouriteMovie, BasicMovieDto.class);
            basicMovieDtos.add(basicMovieDto);
        }
        return basicMovieDtos;
    }

    @Override
    public List<BasicTvShowDto> getFavouriteTvShows(Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        List<BasicTvShowDto> basicTvShowDtos = new ArrayList<>();
        for (TvShowEntity favouriteTvShow : userEntity.getFavouriteTvShows()) {
            BasicTvShowDto basicTvShowDto = this.modelMapper.map(favouriteTvShow, BasicTvShowDto.class);
            basicTvShowDtos.add(basicTvShowDto);
        }
        return basicTvShowDtos;
    }

    @Override
    public List<BasicPersonDto> getFavouritePeople(Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        List<BasicPersonDto> basicPersonDtos = new ArrayList<>();
        for (PersonEntity favouritePerson : userEntity.getFavouritePeople()) {
            BasicPersonDto basicPersonDto = this.modelMapper.map(favouritePerson, BasicPersonDto.class);
            basicPersonDtos.add(basicPersonDto);
        }
        return basicPersonDtos;
    }

    @Override
    public void addFavouriteMovie(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        MovieEntity movieEntity = (MovieEntity) this.showService.getShow(id);
        userEntity.getFavouriteMovies().add(movieEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void addFavouriteTvShow(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        TvShowEntity tvShowEntity = (TvShowEntity) this.showService.getShow(id);
        userEntity.getFavouriteTvShows().add(tvShowEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void addFavouritePerson(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        PersonEntity personEntity = this.personService.getPersonById(id);
        userEntity.getFavouritePeople().add(personEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void removeFavouriteMovie(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        MovieEntity movieEntity = (MovieEntity) this.showService.getShow(id);
        userEntity.getFavouriteMovies().remove(movieEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void removeFavouriteTvShow(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        TvShowEntity tvShowEntity = (TvShowEntity) this.showService.getShow(id);
        userEntity.getFavouriteTvShows().remove(tvShowEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void removeFavouritePerson(String id, Principal principal) {
        String username = (String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow();
        PersonEntity personEntity = this.personService.getPersonById(id);
        userEntity.getFavouritePeople().remove(personEntity);
        this.userRepository.saveAndFlush(userEntity);
    }
}
