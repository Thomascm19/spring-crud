package com.simple.crud.application.controller;

import com.simple.crud.application.mappers.IPhotoRestMapper;
import com.simple.crud.application.model.request.PhotoRequest;
import com.simple.crud.application.model.response.PhotoResponse;
import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.domain.ports.IPhotoServicePort;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final IPhotoServicePort photoServicePort;

    @Operation(summary = "Add a new photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Photo created", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PhotoResponse> addPhoto(
            @RequestParam("id") String id,
            @RequestParam("personId") String personID,
            @RequestParam("file") MultipartFile image
    ) throws IOException {
        PhotoRequest photoRequest = new PhotoRequest();
        photoRequest.setPersonId(Long.parseLong(personID));
        photoRequest.set_id(Long.valueOf(id));
        photoRequest.setPhoto(Base64.getEncoder().encodeToString(image.getBytes()));
        PhotoModel photoModel = IPhotoRestMapper.INSTANCE.mapToPhotoModel(photoRequest);
        PhotoResponse photoResponse = IPhotoRestMapper.INSTANCE.mapToPhotoResponse(
                photoServicePort.addPhoto(photoModel));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(photoResponse);
    }

    @Operation(summary = "Update an existing photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Photo not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<PhotoResponse> updatePhoto(
            @RequestParam("id") String id,
            @RequestParam("personId") String personID,
            @RequestParam("file") MultipartFile image
    ) throws IOException {
        PhotoRequest photoRequest = new PhotoRequest();
        photoRequest.setPersonId(Long.parseLong(personID));
        photoRequest.set_id(Long.valueOf(id));
        photoRequest.setPhoto(Base64.getEncoder().encodeToString(image.getBytes()));
        PhotoModel photoModel = IPhotoRestMapper.INSTANCE.mapToPhotoModel(photoRequest);
        PhotoResponse photoResponse = IPhotoRestMapper.INSTANCE.mapToPhotoResponse(
                photoServicePort.updatePhoto(photoModel));
        return ResponseEntity.ok(photoResponse);
    }

    @Operation(summary = "Get a photo by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhotoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Photo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponse> getPhotoByID(@PathVariable long id) throws BusinessException {
        PhotoResponse photoResponse = IPhotoRestMapper.INSTANCE.mapToPhotoResponse(
                photoServicePort.getPhotoById(id));
        return ResponseEntity.ok(photoResponse);
    }

    @Operation(summary = "Get all the photos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All photos returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PhotoResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PhotoResponse>> getAllPhotos() {
        List<PhotoResponse> photosResponse = IPhotoRestMapper.INSTANCE.mapToPhotoResponseList(
                photoServicePort.getPhotos());
        return ResponseEntity.ok(photosResponse);
    }

    @Operation(summary = "Delete a photo by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Photo not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deletePhotoByID(@PathVariable long id) {
        photoServicePort.deletePhotoById(id);
    }
}
