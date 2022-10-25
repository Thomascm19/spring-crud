package com.simple.crud.domain;

import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.domain.ports.IPhotoPersistencePort;
import com.simple.crud.domain.ports.IPhotoServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PhotoServiceImpl implements IPhotoServicePort {

    private final IPhotoPersistencePort photoPersistencePort;

    @Override
    public PhotoModel addPhoto(PhotoModel photoModel) {
        return this.photoPersistencePort.addPhoto(photoModel);
    }

    @Override
    public void deletePhotoById(Long id) {
        this.photoPersistencePort.deletePhotoById(id);
    }

    @Override
    public PhotoModel updatePhoto(PhotoModel photoModel) {
        return this.photoPersistencePort.updatePhoto(photoModel);
    }

    @Override
    public List<PhotoModel> getPhotos() {
        return this.photoPersistencePort.getPhotos();
    }

    @Override
    public PhotoModel getPhotoById(Long id) throws BusinessException {
        Optional<PhotoModel> photoModelOptional = Optional.ofNullable(
                this.photoPersistencePort.getPhotoById(id)
        );
        return photoModelOptional.orElseThrow(BusinessException::new);
    }
}
