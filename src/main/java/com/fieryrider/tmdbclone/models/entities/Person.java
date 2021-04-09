package com.fieryrider.tmdbclone.models.entities;

import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity {
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
    @ManyToMany
    private Set<Show> knownFor;
    @ManyToMany(mappedBy = "cast")
    private Set<Show> acting;
    @ManyToMany(mappedBy = "producers")
    private Set<Show> producing;
    @ManyToMany(mappedBy = "writers")
    private Set<Show> writing;
    @ManyToMany(mappedBy = "directors")
    private Set<Show> directing;
    @OneToMany(mappedBy = "from")
    private Set<Character> characters;

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

    public Set<Show> getKnownFor() {
        return this.knownFor;
    }

    public void setKnownFor(Set<Show> knownFor) {
        this.knownFor = knownFor;
    }

    public Set<Show> getActing() {
        return this.acting;
    }

    public void setActing(Set<Show> acting) {
        this.acting = acting;
    }

    public Set<Show> getProducing() {
        return this.producing;
    }

    public void setProducing(Set<Show> producing) {
        this.producing = producing;
    }

    public Set<Show> getWriting() {
        return this.writing;
    }

    public void setWriting(Set<Show> writing) {
        this.writing = writing;
    }

    public Set<Show> getDirecting() {
        return this.directing;
    }

    public void setDirecting(Set<Show> directing) {
        this.directing = directing;
    }

    public Set<Character> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Person() {
    }

    public Person(String name, int age, String profilePictureUrl, Gender gender, PersonRole mainRole) {
        this.name = name;
        this.age = age;
        this.profilePictureUrl = profilePictureUrl;
        this.gender = gender;
        this.mainRole = mainRole;
    }
}
