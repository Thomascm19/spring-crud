package com.simple.crud.domain.ports;

import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.model.PersonModel;

import java.util.List;

public interface IPersonServicePort {
    PersonModel addPerson(PersonModel personModel);

    void deletePersonById(Long id);

    PersonModel updatePerson(PersonModel personModel);

    List<PersonModel> getPersons();

    PersonModel getPersonById(Long id) throws BusinessException;
}
