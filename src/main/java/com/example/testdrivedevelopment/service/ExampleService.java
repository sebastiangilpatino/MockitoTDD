package com.example.testdrivedevelopment.service;

import com.example.testdrivedevelopment.model.Person;
import com.example.testdrivedevelopment.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExampleService {
    private final PersonRepo personRepo;

    @Autowired
    public ExampleService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person getPersonUpperCase(String id) {
        Optional<Person> personOptional = personRepo.getPersonById(id);
        Person person;

        if (personOptional.isPresent()) {
            person = personOptional.get();
            return upperCasePersonName(person);
        }

        return null;
    }

    private Person upperCasePersonName(Person person){
        person.setFName(person.getFName().toUpperCase());
        person.setLName(person.getLName().toUpperCase());
        return person;
    }
}
