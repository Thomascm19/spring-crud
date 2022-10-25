package com.simple.crud.domain.ports;

import com.simple.crud.domain.model.PhotoModel;

import java.util.List;

public interface IPhotoPersistencePort {
    PhotoModel addPhoto(PhotoModel photoModel);

    void deletePhotoById(Long id);

    PhotoModel updatePhoto(PhotoModel photoModel);

    List<PhotoModel> getPhotos();

    PhotoModel getPhotoById(Long id);
}
