package com.simple.crud.domain.exception;

public enum ExceptionResponse {

    PERSON_NOT_FOUND("No Person was found with that ID"),
    PERSON_ALREADY_EXISTS("There is already a person with that ID"),
    TYPE_NOT_FOUND("No type was found for a person"),
    NO_DATA_FOUND("No data found for the requested petition"),
    PHOTO_NOT_FOUND("No photo was found for a person");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
