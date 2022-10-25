package com.simple.crud.infrastructure.adapters;

import com.simple.crud.domain.exception.NoDataFoundException;
import com.simple.crud.domain.exception.PhotoNotFoundException;
import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.infrastructure.entity.PhotoEntity;
import com.simple.crud.infrastructure.repository.IPhotoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class PhotoMongoAdapterTests {

    @Mock
    IPhotoRepository mockPhotoRepository;

    @InjectMocks
    private PhotoMongoAdapter photoMongoAdapter;

    @Test
    public void mustAddPhoto() {
        PhotoEntity fakePhotoEntity = getFakePhotoEntity();

        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoRepository.save(fakePhotoEntity)).thenReturn(fakePhotoEntity);

        PhotoModel photoModel = photoMongoAdapter.addPhoto(fakePhotoModel);

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustDeletePhoto() {
        PhotoEntity fakePhotoEntity = getFakePhotoEntity();

        doNothing().when(mockPhotoRepository).deleteById(fakePhotoEntity.get_id());
    }

    @Test
    public void mustUpdatePhoto() {
        PhotoEntity fakePhotoEntity = getFakePhotoEntity();

        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoRepository.save(fakePhotoEntity)).thenReturn(fakePhotoEntity);

        PhotoModel photoModel = photoMongoAdapter.updatePhoto(fakePhotoModel);

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustGetAllPhotos() {
        PhotoEntity fakePhotoEntity = getFakePhotoEntity();

        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoRepository.findAll()).thenReturn(List.of(fakePhotoEntity));

        List<PhotoModel> photoModel = photoMongoAdapter.getPhotos();

        assertEquals(photoModel, List.of(fakePhotoModel));
    }

    @Test
    public void mustGetPhotosById() {
        PhotoEntity fakePhotoEntity = getFakePhotoEntity();

        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoRepository.findById(fakePhotoEntity.get_id())).thenReturn(Optional.of(fakePhotoEntity));

        PhotoModel photoModel = photoMongoAdapter.getPhotoById(fakePhotoEntity.get_id());

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustThrowNoDataFoundException() {
        List<PhotoEntity> fakePhotoEntityList = Collections.emptyList();

        when(mockPhotoRepository.findAll()).thenReturn(fakePhotoEntityList);

        assertThrows(NoDataFoundException.class, () -> photoMongoAdapter.getPhotos());
    }

    @Test
    public void mustThrowPhotoNotFoundException() {
        Long fakeId = 1L;

        when(mockPhotoRepository.findById(fakeId)).thenReturn(Optional.empty());

        assertThrows(PhotoNotFoundException.class, () -> photoMongoAdapter.getPhotoById(fakeId));
    }

    private static PhotoModel getFakePhotoModel() {
        PhotoModel photoModel = new PhotoModel();
        photoModel.set_id(1L);
        photoModel.setPersonId(1L);
        photoModel.setPhoto("testPhoto");
        return photoModel;
    }

    private static PhotoEntity getFakePhotoEntity() {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.set_id(1L);
        photoEntity.setPersonId(1L);
        photoEntity.setPhoto("testPhoto");
        return photoEntity;
    }
}
