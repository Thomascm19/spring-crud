package com.simple.crud.application.exception;

import com.simple.crud.application.model.response.ErrorResponse;
import com.simple.crud.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlePersonAlreadyExistsException(
            PersonAlreadyExistsException personAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PERSON_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePersonNotFoundException(
            PersonNotFoundException personNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PERSON_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(PhotoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePhotoNotFoundException(
            PhotoNotFoundException photoNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHOTO_NOT_FOUND.getMessage()));
    }

}