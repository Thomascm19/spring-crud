package com.simple.crud.infrastructure.adapters;

import com.simple.crud.domain.exception.NoDataFoundException;
import com.simple.crud.domain.exception.PhotoNotFoundException;
import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.domain.ports.IPhotoPersistencePort;
import com.simple.crud.infrastructure.entity.PhotoEntity;
import com.simple.crud.infrastructure.mappers.IPhotoMapper;
import com.simple.crud.infrastructure.repository.IPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoMongoAdapter implements IPhotoPersistencePort {

    private final IPhotoRepository photoRepository;

    @Override
    public PhotoModel addPhoto(PhotoModel photoModel) {
        PhotoEntity photoEntity = IPhotoMapper.INSTANCE.mapToPhotoEntity(photoModel);
        PhotoEntity photoSaved = this.photoRepository.save(photoEntity);
        return IPhotoMapper.INSTANCE.mapToPhotoModel(photoSaved);
    }

    @Override
    public void deletePhotoById(Long id) {
        this.photoRepository.deleteById(id);
    }

    @Override
    public PhotoModel updatePhoto(PhotoModel photoModel) {
        return this.addPhoto(photoModel);
    }

    @Override
    public List<PhotoModel> getPhotos() {
        List<PhotoEntity> photoEntityList = this.photoRepository.findAll();
        if (photoEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return IPhotoMapper.INSTANCE.mapToPhotoListModel(photoEntityList);
    }

    @Override
    public PhotoModel getPhotoById(Long id) {
        Optional<PhotoEntity> photoEntity = this.photoRepository.findById(id);
        return photoEntity.map(IPhotoMapper.INSTANCE::mapToPhotoModel)
                .orElseThrow(PhotoNotFoundException::new);
    }
}
