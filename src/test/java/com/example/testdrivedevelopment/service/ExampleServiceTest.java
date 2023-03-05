package com.example.testdrivedevelopment.service;

import com.example.testdrivedevelopment.model.Person;
import com.example.testdrivedevelopment.repository.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

class ExampleServiceTest {

    @InjectMocks
    private ExampleService service;

    @Spy //mock class but not override actions
    private PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPersonUpperCase_ReturnExpected() {
        Person actual = service.getPersonUpperCase("1");
        Person expectedPerson = new Person("JORGE", "GIL");
        Assertions.assertEquals(expectedPerson, actual);
    }

    //Usage of verify
    @Test
    void upperCasePersonNameTest() throws Exception {
        //create an object
        Person person = Mockito.mock(Person.class);
        Optional<Person> personMock = Optional.of(person);
        //pass the object to the tested class
        //When @Spy we can use mockito when as well as @Mock
        Mockito.when(personRepo.getPersonById(Mockito.anyString()))
                .thenReturn(personMock);
        //mock behaviour
        Mockito.when(person.getFName()).thenReturn("");
        Mockito.when(person.getLName()).thenReturn("");
        //start test
        service.getPersonUpperCase("5");
        //Assert that setter was called.
        Mockito.verify(person, Mockito.times(1)).setFName(Mockito.anyString());
        Mockito.verify(person, Mockito.times(1)).setLName(Mockito.anyString());
    }
}