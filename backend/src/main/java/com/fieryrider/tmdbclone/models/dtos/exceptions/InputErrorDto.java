package com.fieryrider.tmdbclone.models.dtos.exceptions;

public class InputErrorDto {
    private String message;
    private String constraint;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConstraint() {
        return this.constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public InputErrorDto() {
    }

    public InputErrorDto(String message, String constraint) {
        this.message = message;
        this.constraint = constraint;
    }
}
