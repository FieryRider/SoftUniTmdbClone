package com.fieryrider.tmdbclone.services;

import com.fieryrider.tmdbclone.models.dtos.BasicMovieDto;
import com.fieryrider.tmdbclone.models.dtos.BasicPersonDto;
import com.fieryrider.tmdbclone.models.dtos.BasicTvShowDto;
import com.fieryrider.tmdbclone.models.dtos.UserRegisterDto;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void initAdminUser();
    void registerUser(UserRegisterDto userRegisterDto);
    List<BasicMovieDto> getFavouriteMovies(Principal principal);
    List<BasicTvShowDto> getFavouriteTvShows(Principal principal);
    List<BasicPersonDto> getFavouritePeople(Principal principal);
    void addFavouriteMovie(String id, Principal principal);
    void addFavouriteTvShow(String id, Principal principal);
    void addFavouritePerson(String id, Principal principal);
}
