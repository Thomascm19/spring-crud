package com.simple.crud.application.mappers;

import com.simple.crud.application.model.request.PhotoRequest;
import com.simple.crud.application.model.response.PhotoResponse;
import com.simple.crud.domain.model.PhotoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPhotoRestMapper {
    IPhotoRestMapper INSTANCE = Mappers.getMapper(IPhotoRestMapper.class);

    PhotoModel mapToPhotoModel(PhotoRequest PhotoRequest);

    PhotoResponse mapToPhotoResponse(PhotoModel PhotoModel);

    List<PhotoResponse> mapToPhotoResponseList(List<PhotoModel> PhotoListModel);
}
