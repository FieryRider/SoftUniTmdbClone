package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void initAdminUser();
    void registerUser(UserRegisterDto userRegisterDto);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    List<BasicMovieDto> getFavouriteMovies(Principal principal);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    List<BasicTvShowDto> getFavouriteTvShows(Principal principal);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    List<BasicPersonDto> getFavouritePeople(Principal principal);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    void addFavouriteMovie(String id, Principal principal);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    void addFavouriteTvShow(String id, Principal principal);

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    void addFavouritePerson(String id, Principal principal);
}
