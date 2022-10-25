package com.simple.crud.domain.ports;

import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.model.PhotoModel;

import java.util.List;

public interface IPhotoServicePort {
    PhotoModel addPhoto(PhotoModel PhotoModel);

    void deletePhotoById(Long id);

    PhotoModel updatePhoto(PhotoModel PhotoModel);

    List<PhotoModel> getPhotos();

    PhotoModel getPhotoById(Long id) throws BusinessException;
}
