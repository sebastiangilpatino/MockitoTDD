package com.example.testdrivedevelopment.repository;

import com.example.testdrivedevelopment.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PersonRepo {
    private Map<String, Person> personMap;

    public PersonRepo() {
        this.personMap = new HashMap<>();
        this.personMap.put("1", new Person("jorge", "gil"));
        this.personMap.put("2", new Person("maria", "estrada"));
        this.personMap.put("3", new Person("tomas", "vargas"));
    }

    public Optional<Person> getPersonById(String id) {
        Person person = this.personMap.get(id);
        if (person == null) return Optional.empty();
        return Optional.of(person);
    }
}
