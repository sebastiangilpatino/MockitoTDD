package com.example.testdrivedevelopment.controller;

import com.example.testdrivedevelopment.model.Person;
import com.example.testdrivedevelopment.service.ExampleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ExampleControllerTest {
    //we are using constructor @Autowired
    //this approach seems that is not working
    //@InjectMocks
    //private ExampleController controller;
    @Mock
    private ExampleService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPersonByIdFromWebApp_ReturnOK() {
        Person person1 = new Person("jorge", "gil");
        Mockito.when(service.getPersonUpperCase(Mockito.anyString()))
                .thenReturn(person1);
        //Pass the mocked object through constructor
        ExampleController controller = new ExampleController(service);
        ResponseEntity<?> actualResponsePerson = controller.getPersonByIdFromWebApp("1");
        ResponseEntity<?> expectedResponsePerson = new ResponseEntity<>(
                person1, HttpStatus.OK
        );
        Assertions.assertEquals(expectedResponsePerson, actualResponsePerson);
    }
    @Test
    void getPersonByIdFromWebApp_ReturnNOTFOUND() {
        Mockito.when(service.getPersonUpperCase(Mockito.anyString()))
                .thenReturn(null);
        ExampleController controller = new ExampleController(service);
        ResponseEntity<?> actualResponsePerson = controller.getPersonByIdFromWebApp("1");
        ResponseEntity<?> expectedResponsePerson = new ResponseEntity<>(
                "", HttpStatus.NOT_FOUND
        );
        //Only checking the status code
        Assertions.assertEquals(expectedResponsePerson.getStatusCode(), actualResponsePerson.getStatusCode());
    }
}