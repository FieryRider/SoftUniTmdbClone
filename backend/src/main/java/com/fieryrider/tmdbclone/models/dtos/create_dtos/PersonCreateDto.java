package com.fieryrider.tmdbclone.models.dtos.create_dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;
import com.fieryrider.tmdbclone.validation.EnumNameValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PersonCreateDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    private String biography;

    private String placeOfBirth;

    @NotNull(message = "Profile picture URL cannot be null")
    @NotBlank(message = "Profile picture URL cannot be empty")
    private String profilePictureUrl;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

    @NotNull(message = "Gender cannot be null")
    @EnumNameValid(enumClass = Gender.class, message = "Gender must be one of the predefined")
    private String gender;

    @NotNull(message = "Main Role cannot be null")
    @EnumNameValid(enumClass = PersonRole.class, message = "Person role must be one of the predefined")
    private String mainRole;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMainRole() {
        return this.mainRole;
    }

    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

    public PersonCreateDto() {
    }
}
