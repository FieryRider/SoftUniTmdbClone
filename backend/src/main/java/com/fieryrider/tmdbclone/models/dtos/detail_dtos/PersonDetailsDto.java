package com.fieryrider.tmdbclone.models.dtos.detail_dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.EnumDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.PersonKnownForDto;
import com.fieryrider.tmdbclone.models.dtos.property_dtos.PersonShowDto;

import java.time.LocalDate;
import java.util.Set;

public class PersonDetailsDto {
    private String id;

    private String name;

    private int age;

    private String biography;

    private String placeOfBirth;

    private String profilePictureUrl;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T00:00:00.000Z'")
    private LocalDate birthDate;

    private EnumDto gender;

    private EnumDto mainRole;

    private int knownCredits;

    private Set<PersonKnownForDto> knownFor;

    private Set<PersonShowDto> acting;

    private Set<PersonShowDto> producing;

    private Set<PersonShowDto> writing;

    private Set<PersonShowDto> directing;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public EnumDto getGender() {
        return this.gender;
    }

    public void setGender(EnumDto gender) {
        this.gender = gender;
    }

    public EnumDto getMainRole() {
        return this.mainRole;
    }

    public void setMainRole(EnumDto mainRole) {
        this.mainRole = mainRole;
    }

    public int getKnownCredits() {
        return this.knownCredits;
    }

    public void setKnownCredits(int knownCredits) {
        this.knownCredits = knownCredits;
    }

    public Set<PersonKnownForDto> getKnownFor() {
        return this.knownFor;
    }

    public void setKnownFor(Set<PersonKnownForDto> knownFor) {
        this.knownFor = knownFor;
    }

    public Set<PersonShowDto> getActing() {
        return this.acting;
    }

    public void setActing(Set<PersonShowDto> acting) {
        this.acting = acting;
    }

    public Set<PersonShowDto> getProducing() {
        return this.producing;
    }

    public void setProducing(Set<PersonShowDto> producing) {
        this.producing = producing;
    }

    public Set<PersonShowDto> getWriting() {
        return this.writing;
    }

    public void setWriting(Set<PersonShowDto> writing) {
        this.writing = writing;
    }

    public Set<PersonShowDto> getDirecting() {
        return this.directing;
    }

    public void setDirecting(Set<PersonShowDto> directing) {
        this.directing = directing;
    }

    public PersonDetailsDto() {
    }
}