package com.simple.crud.infrastructure.mappers;

import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.infrastructure.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface IPersonMapper {

    IPersonMapper INSTANCE = Mappers.getMapper(IPersonMapper.class);

    PersonModel mapToPersonModel(PersonEntity personEntity);

    PersonEntity mapToPersonEntity(PersonModel personModel);

    List<PersonModel> mapToPersonListModel(List<PersonEntity> personListEntity);

    List<PersonEntity> mapToPersonListEntity(List<PersonModel> personListModel);
}
