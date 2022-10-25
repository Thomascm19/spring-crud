package com.simple.crud.domain;

import com.simple.crud.domain.exception.BusinessException;
import com.simple.crud.domain.exception.PersonNotFoundException;
import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.domain.ports.IPersonPersistencePort;
import com.simple.crud.domain.ports.IPersonServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonServicePort {

    private final IPersonPersistencePort personPersistencePort;

    @Override
    public PersonModel addPerson(PersonModel personModel) {
        return this.personPersistencePort.addPerson(personModel, false);
    }

    @Override
    public void deletePersonById(Long id) {
        this.personPersistencePort.deletePersonById(id);
    }

    @Override
    public PersonModel updatePerson(PersonModel personModel) {
        return this.personPersistencePort.updatePerson(personModel);
    }

    @Override
    public List<PersonModel> getPersons() {
        return this.personPersistencePort.getPersons();
    }

    @Override
    public PersonModel getPersonById(Long id) throws BusinessException {
        Optional<PersonModel> personModelOptional = Optional.ofNullable(
                this.personPersistencePort.getPersonById(id)
        );
        return personModelOptional.orElseThrow(PersonNotFoundException::new);
    }
}
