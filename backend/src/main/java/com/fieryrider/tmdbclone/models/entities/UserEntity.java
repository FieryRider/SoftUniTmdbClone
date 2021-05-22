package com.fieryrider.tmdbclone.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;

    private String profilePictureUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourite_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id"))
    private Set<MovieEntity> favouriteMovies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourite_tvshows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id"))
    private Set<TvShowEntity> favouriteTvShows;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourite_people",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<PersonEntity> favouritePeople;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRoleEntity> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Set<MovieEntity> getFavouriteMovies() {
        return this.favouriteMovies;
    }

    public void setFavouriteMovies(Set<MovieEntity> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    public Set<TvShowEntity> getFavouriteTvShows() {
        return this.favouriteTvShows;
    }

    public void setFavouriteTvShows(Set<TvShowEntity> favouriteTvShows) {
        this.favouriteTvShows = favouriteTvShows;
    }

    public Set<PersonEntity> getFavouritePeople() {
        return this.favouritePeople;
    }

    public void setFavouritePeople(Set<PersonEntity> favouritePeople) {
        this.favouritePeople = favouritePeople;
    }

    public UserEntity() {
    }

    public UserEntity(String username, String password, String email, Set<UserRoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
