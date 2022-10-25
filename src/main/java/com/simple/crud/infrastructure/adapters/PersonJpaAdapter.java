package com.simple.crud.infrastructure.adapters;

import com.simple.crud.domain.exception.NoDataFoundException;
import com.simple.crud.domain.exception.PersonAlreadyExistsException;
import com.simple.crud.domain.exception.PersonNotFoundException;
import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.domain.ports.IPersonPersistencePort;
import com.simple.crud.infrastructure.entity.PersonEntity;
import com.simple.crud.infrastructure.mappers.IPersonMapper;
import com.simple.crud.infrastructure.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonJpaAdapter implements IPersonPersistencePort {
    private final IPersonRepository personRepository;

    @Override
    public PersonModel addPerson(PersonModel personModel, boolean isPersonUpdate) {
        PersonEntity personEntity = IPersonMapper.INSTANCE.mapToPersonEntity(personModel);
        if (personRepository.findById(personEntity.getId()).isPresent() && !isPersonUpdate) {
            throw new PersonAlreadyExistsException();
        }
        PersonEntity personSaved = this.personRepository.save(personEntity);
        return IPersonMapper.INSTANCE.mapToPersonModel(personSaved);
    }

    @Override
    public void deletePersonById(Long id) {
        Optional<PersonEntity> personEntity = this.personRepository.findById(id);
        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException();
        }
        this.personRepository.deleteById(id);
    }

    @Override
    public PersonModel updatePerson(PersonModel personModel) {
        boolean isPersonUpdate = true;
        return this.addPerson(personModel, isPersonUpdate);
    }

    @Override
    public List<PersonModel> getPersons() {
        List<PersonEntity> personEntityList = this.personRepository.findAll();
        if (personEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return IPersonMapper.INSTANCE.mapToPersonListModel(personEntityList);
    }

    @Override
    public PersonModel getPersonById(Long id) {
        Optional<PersonEntity> personEntity = this.personRepository.findById(id);
        return personEntity.map(IPersonMapper.INSTANCE::mapToPersonModel)
                .orElseThrow(PersonNotFoundException::new);
    }
}
