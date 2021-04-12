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
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Show> knownFor;
    @ManyToMany(mappedBy = "cast", fetch = FetchType.EAGER)
    private Set<Show> acting;
    @ManyToMany(mappedBy = "producers", fetch = FetchType.EAGER)
    private Set<Movie> producing;
    @ManyToMany(mappedBy = "writers", fetch = FetchType.EAGER)
    private Set<Movie> writing;
    @ManyToMany(mappedBy = "directors", fetch = FetchType.EAGER)
    private Set<Movie> directing;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "people_characters",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> playing;

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

    public Set<Movie> getProducing() {
        return this.producing;
    }

    public void setProducing(Set<Movie> producing) {
        this.producing = producing;
    }

    public Set<Movie> getWriting() {
        return this.writing;
    }

    public void setWriting(Set<Movie> writing) {
        this.writing = writing;
    }

    public Set<Movie> getDirecting() {
        return this.directing;
    }

    public void setDirecting(Set<Movie> directing) {
        this.directing = directing;
    }

    public Set<Character> getPlaying() {
        return this.playing;
    }

    public void setPlaying(Set<Character> playing) {
        this.playing = playing;
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
