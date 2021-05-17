package com.fieryrider.tmdbclone.models.dtos.update_dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fieryrider.tmdbclone.models.entities.enums.Gender;
import com.fieryrider.tmdbclone.models.entities.enums.PersonRole;
import com.fieryrider.tmdbclone.validation.EnumNameValid;
import com.fieryrider.tmdbclone.validation.NullOrNotBlank;

import javax.validation.constraints.Min;
import java.time.LocalDate;

public class PersonUpdateDto {
    @NullOrNotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    private String biography;

    private String placeOfBirth;

    @NullOrNotBlank(message = "Profile picture URL cannot be empty")
    private String profilePictureUrl;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

    @EnumNameValid(enumClass = Gender.class, message = "Gender must be one of the predefined")
    private String gender;

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

    public PersonUpdateDto() {
    }
}
