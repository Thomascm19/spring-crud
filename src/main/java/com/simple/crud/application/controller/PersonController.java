package com.simple.crud.application.controller;

import com.simple.crud.application.mappers.IPersonRestMapper;
import com.simple.crud.application.model.request.PersonRequest;
import com.simple.crud.application.model.response.PersonResponse;
import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.domain.ports.IPersonServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonServicePort personServicePort;

    @Operation(summary = "Add a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Person already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody PersonRequest personRequest) {
        PersonModel personModel = IPersonRestMapper.INSTANCE.mapToPersonModel(personRequest);
        PersonResponse personResponse = IPersonRestMapper.INSTANCE.mapToPersonResponse(
                personServicePort.addPerson(personModel));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personResponse);
    }

    @Operation(summary = "Update an existing person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody PersonRequest personRequest) {
        PersonModel personModel = IPersonRestMapper.INSTANCE.mapToPersonModel(personRequest);
        PersonResponse personResponse = IPersonRestMapper.INSTANCE.mapToPersonResponse(
                personServicePort.updatePerson(personModel));
        return ResponseEntity.ok(personResponse);
    }

    @Operation(summary = "Get a persons by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonByID(@PathVariable long id)
            throws BusinessException {
        PersonResponse personResponse = IPersonRestMapper.INSTANCE.mapToPersonResponse(
                personServicePort.getPersonById(id));
        return ResponseEntity.ok(personResponse);
    }

    @Operation(summary = "Get all the persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All persons returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        List<PersonResponse> personsResponse = IPersonRestMapper.INSTANCE.mapToPersonResponseList(
                personServicePort.getPersons());
        return ResponseEntity.ok(personsResponse);
    }

    @Operation(summary = "Delete a person by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonByID(@PathVariable long id) {
        personServicePort.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
