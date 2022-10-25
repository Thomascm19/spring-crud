package com.simple.crud.application.mappers;

import com.simple.crud.application.model.request.PersonRequest;
import com.simple.crud.application.model.response.PersonResponse;
import com.simple.crud.domain.model.PersonModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPersonRestMapper {

    IPersonRestMapper INSTANCE = Mappers.getMapper(IPersonRestMapper.class);

    PersonModel mapToPersonModel(PersonRequest personRequest);

    PersonResponse mapToPersonResponse(PersonModel personModel);

    List<PersonResponse> mapToPersonResponseList(List<PersonModel> personListModel);
}