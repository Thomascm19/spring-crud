package com.simple.crud.domain;

import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.domain.ports.IPhotoPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PhotoServiceImplTest {

    PhotoServiceImpl photoServiceImplMock = mock(PhotoServiceImpl.class);
    @Mock
    IPhotoPersistencePort mockPhotoPersistencePort;

    @InjectMocks
    private PhotoServiceImpl photoServiceImpl;

    @Test
    public void mustAddPhoto() {
        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoPersistencePort.addPhoto(fakePhotoModel)).thenReturn(fakePhotoModel);

        PhotoModel photoModel = photoServiceImpl.addPhoto(fakePhotoModel);

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustDeletePhoto() {
        PhotoModel fakePhotoModel = new PhotoModel();
        fakePhotoModel.set_id(1L);
        doNothing().when(photoServiceImplMock).deletePhotoById(fakePhotoModel.get_id());
    }

    @Test
    public void mustUpdatePhoto() {
        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoPersistencePort.updatePhoto(fakePhotoModel)).thenReturn(fakePhotoModel);

        PhotoModel photoModel = photoServiceImpl.updatePhoto(fakePhotoModel);

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustGetPhotoById() {
        PhotoModel fakePhotoModel = getFakePhotoModel();

        when(mockPhotoPersistencePort.getPhotoById(fakePhotoModel.get_id())).thenReturn(fakePhotoModel);

        PhotoModel photoModel = photoServiceImpl.getPhotoById(fakePhotoModel.get_id());

        assertEquals(photoModel, fakePhotoModel);
    }

    @Test
    public void mustGetPhotos() {
        List<PhotoModel> fakePhotoModel = getFakePhotosModel();

        when(mockPhotoPersistencePort.getPhotos()).thenReturn(fakePhotoModel);

        List<PhotoModel> photoModel = photoServiceImpl.getPhotos();

        assertEquals(photoModel, fakePhotoModel);
    }

    private List<PhotoModel> getFakePhotosModel() {
        return List.of(
                new PhotoModel(1L, 1L, "testPhoto"),
                new PhotoModel(2L, 2L, "testPhoto2"),
                new PhotoModel(3L, 3L, "testPhoto3")
        );
    }

    private static PhotoModel getFakePhotoModel() {
        PhotoModel photoModel = new PhotoModel();
        photoModel.set_id(1L);
        photoModel.setPersonId(1L);
        photoModel.setPhoto("testPhoto");
        return photoModel;
    }
}
