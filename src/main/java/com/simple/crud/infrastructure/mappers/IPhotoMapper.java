package com.simple.crud.infrastructure.mappers;

import com.simple.crud.domain.model.PhotoModel;
import com.simple.crud.infrastructure.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPhotoMapper {
    IPhotoMapper INSTANCE = Mappers.getMapper(IPhotoMapper.class);

    PhotoModel mapToPhotoModel(PhotoEntity photoEntity);

    PhotoEntity mapToPhotoEntity(PhotoModel photoModel);

    List<PhotoModel> mapToPhotoListModel(List<PhotoEntity> photoListEntity);

    List<PhotoEntity> mapToPhotoListEntity(List<PhotoModel> photoListModel);
}
