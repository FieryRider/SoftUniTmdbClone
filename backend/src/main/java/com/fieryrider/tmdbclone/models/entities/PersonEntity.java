package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(columnDefinition = "TEXT")
    private String biography;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "profile_picture_url", nullable = false)
    private String profilePictureUrl;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonRole mainRole;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ShowEntity> knownFor;
    @ManyToMany(mappedBy = "cast", fetch = FetchType.EAGER)
    private Set<ShowEntity> acting;
    @ManyToMany(mappedBy = "producers", fetch = FetchType.EAGER)
    private Set<MovieEntity> producing;
    @ManyToMany(mappedBy = "writers", fetch = FetchType.EAGER)
    private Set<MovieEntity> writing;
    @ManyToMany(mappedBy = "directors", fetch = FetchType.EAGER)
    private Set<MovieEntity> directing;
    @ManyToMany(mappedBy = "creators", fetch = FetchType.EAGER)
    private Set<TvShowEntity> creating;

    private boolean popular;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PersonRole getMainRole() {
        return this.mainRole;
    }

    public void setMainRole(PersonRole mainRole) {
        this.mainRole = mainRole;
    }

    public Set<ShowEntity> getKnownFor() {
        return this.knownFor;
    }

    public void setKnownFor(Set<ShowEntity> knownFor) {
        this.knownFor = knownFor;
    }

    public Set<ShowEntity> getActing() {
        return this.acting;
    }

    public void setActing(Set<ShowEntity> acting) {
        this.acting = acting;
    }

    public Set<MovieEntity> getProducing() {
        return this.producing;
    }

    public void setProducing(Set<MovieEntity> producing) {
        this.producing = producing;
    }

    public Set<MovieEntity> getWriting() {
        return this.writing;
    }

    public void setWriting(Set<MovieEntity> writing) {
        this.writing = writing;
    }

    public Set<MovieEntity> getDirecting() {
        return this.directing;
    }

    public void setDirecting(Set<MovieEntity> directing) {
        this.directing = directing;
    }

    public Set<TvShowEntity> getCreating() {
        return this.creating;
    }

    public void setCreating(Set<TvShowEntity> creating) {
        this.creating = creating;
    }

    public boolean isPopular() {
        return this.popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public PersonEntity() {
    }

    public PersonEntity(String name, int age, String profilePictureUrl, Gender gender, PersonRole mainRole) {
        this.name = name;
        this.age = age;
        this.profilePictureUrl = profilePictureUrl;
        this.gender = gender;
        this.mainRole = mainRole;
        this.popular = false;
    }
}
