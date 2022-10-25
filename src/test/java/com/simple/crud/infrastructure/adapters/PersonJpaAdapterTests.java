package com.simple.crud.infrastructure.adapters;

import com.simple.crud.domain.exception.NoDataFoundException;
import com.simple.crud.domain.exception.PersonAlreadyExistsException;
import com.simple.crud.domain.exception.PersonNotFoundException;
import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.infrastructure.entity.PersonEntity;
import com.simple.crud.infrastructure.repository.IPersonRepository;
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
class PersonJpaAdapterTests {

    @Mock
    IPersonRepository mockPersonRepository;

    @InjectMocks
    private PersonJpaAdapter personJpaAdapter;

    @Test
    public void mustAddPerson() {
        PersonEntity fakePersonEntity = getFakePersonEntity();

        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonRepository.save(fakePersonEntity)).thenReturn(fakePersonEntity);

        PersonModel personModel = personJpaAdapter.addPerson(fakePersonModel, false);

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustGetAllPersons() {
        List<PersonModel> fakePersonModelList = getFakePersonModelList();

        List<PersonEntity> fakePersonEntityList = getFakePersonEntityList();

        when(mockPersonRepository.findAll()).thenReturn(fakePersonEntityList);

        List<PersonModel> personModelList = personJpaAdapter.getPersons();

        assertEquals(personModelList, fakePersonModelList);
    }

    @Test
    public void mustGetPersonById() {
        PersonEntity fakePersonEntity = getFakePersonEntity();

        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonRepository.findById(fakePersonEntity.getId())).thenReturn(java.util.Optional.of(fakePersonEntity));

        PersonModel personModel = personJpaAdapter.getPersonById(fakePersonEntity.getId());

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustUpdatePerson() {
        PersonEntity fakePersonEntity = getFakePersonEntity();

        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonRepository.save(fakePersonEntity)).thenReturn(fakePersonEntity);

        PersonModel personModel = personJpaAdapter.updatePerson(fakePersonModel);

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustDeletePersonById() {
        PersonEntity fakePersonEntity = getFakePersonEntity();

        doNothing().when(mockPersonRepository).deleteById(fakePersonEntity.getId());
    }

    @Test
    public void mustThrowNoDataFoundException() {
        List<PersonEntity> fakePersonEntityList = Collections.emptyList();

        when(mockPersonRepository.findAll()).thenReturn(fakePersonEntityList);

        assertThrows(NoDataFoundException.class, () -> personJpaAdapter.getPersons());
    }

    @Test
    public void mustThrowPersonNotFoundException() {
        Long fakeId = 1L;

        when(mockPersonRepository.findById(fakeId)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personJpaAdapter.getPersonById(fakeId));
    }

    @Test
    public void mustThrowPersonAlreadyExistsException() {
        PersonEntity fakePersonEntity = getFakePersonEntity();

        PersonModel fakePersonModel = getFakePersonModel();

        Long fakeId = 1L;

        when(mockPersonRepository.findById(fakeId)).thenReturn(Optional.of(fakePersonEntity));

        assertThrows(PersonAlreadyExistsException.class, () -> personJpaAdapter.addPerson(fakePersonModel, false));
    }

    private static List<PersonEntity> getFakePersonEntityList() {
        return List.of(
                new PersonEntity(1L, 10616270, 1, 24, "Thomas", "Caycedo", "Manizales"),
                new PersonEntity(2L, 10616271, 2, 24, "Pedro", "Martinez", "Manizales"),
                new PersonEntity(3L, 10616272, 3, 24, "Juan", "Restrepo", "Manizales")
        );
    }

    private static PersonEntity getFakePersonEntity() {
        PersonEntity fakePersonModel = new PersonEntity();
        fakePersonModel.setId(1L);
        fakePersonModel.setName("Thomas");
        fakePersonModel.setLastName("Caycedo");
        fakePersonModel.setAge(24);
        fakePersonModel.setCityBorn("Manizales");
        fakePersonModel.setDocumentNumber(1061627057);
        fakePersonModel.setTypeDocument(1);
        return fakePersonModel;
    }

    private static List<PersonModel> getFakePersonModelList() {
        return List.of(
                new PersonModel(1L, 10616270, 1, 24, "Thomas", "Caycedo", "Manizales"),
                new PersonModel(2L, 10616271, 2, 24, "Pedro", "Martinez", "Manizales"),
                new PersonModel(3L, 10616272, 3, 24, "Juan", "Restrepo", "Manizales")
        );
    }

    private static PersonModel getFakePersonModel() {
        PersonModel fakePersonModel = new PersonModel();
        fakePersonModel.setId(1L);
        fakePersonModel.setName("Thomas");
        fakePersonModel.setLastName("Caycedo");
        fakePersonModel.setAge(24);
        fakePersonModel.setCityBorn("Manizales");
        fakePersonModel.setDocumentNumber(1061627057);
        fakePersonModel.setTypeDocument(1);
        return fakePersonModel;
    }

}
