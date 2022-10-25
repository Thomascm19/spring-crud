package com.simple.crud.domain.ports;

import com.simple.crud.domain.model.PersonModel;

import java.util.List;

public interface IPersonPersistencePort {
    PersonModel addPerson(PersonModel personModel, boolean isPersonUpdate);

    void deletePersonById(Long id);

    PersonModel updatePerson(PersonModel personModel);

    List<PersonModel> getPersons();

    PersonModel getPersonById(Long id);
}
