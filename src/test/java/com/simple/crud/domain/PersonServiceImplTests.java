package com.simple.crud.domain;

import com.simple.crud.domain.model.PersonModel;
import com.simple.crud.domain.ports.IPersonPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceImplTests {

    PersonServiceImpl personServiceImplMock = mock(PersonServiceImpl.class);
    @Mock
    IPersonPersistencePort mockPersonPersistencePort;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @Test
    public void mustAddUser() {
        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonPersistencePort.addPerson(fakePersonModel, false)).thenReturn(fakePersonModel);

        PersonModel personModel = personServiceImpl.addPerson(fakePersonModel);

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustDeleteUser() {
        PersonModel fakePersonModel = new PersonModel();
        fakePersonModel.setId(1L);
        doNothing().when(personServiceImplMock).deletePersonById(fakePersonModel.getId());
    }

    @Test
    public void mustUpdateUser() {
        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonPersistencePort.updatePerson(fakePersonModel)).thenReturn(fakePersonModel);

        PersonModel personModel = personServiceImpl.updatePerson(fakePersonModel);

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustGetUserById() {
        PersonModel fakePersonModel = getFakePersonModel();

        when(mockPersonPersistencePort.getPersonById(fakePersonModel.getId())).thenReturn(fakePersonModel);

        PersonModel personModel = personServiceImpl.getPersonById(fakePersonModel.getId());

        assertEquals(personModel, fakePersonModel);
    }

    @Test
    public void mustGetAllUsers() {
        List<PersonModel> fakePersonsModel = getFakePersonModelList();

        when(mockPersonPersistencePort.getPersons()).thenReturn(fakePersonsModel);

        List<PersonModel> personModelList = personServiceImpl.getPersons();

        assertEquals(personModelList, fakePersonsModel);
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
