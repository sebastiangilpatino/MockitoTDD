package com.example.testdrivedevelopment.controller;

import com.example.testdrivedevelopment.model.Person;
import com.example.testdrivedevelopment.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {
    private final ExampleService service;

    @Autowired
    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPersonByIdFromWebApp(@PathVariable String id) {
        Person person = service.getPersonUpperCase(id);
        if (person == null) return new ResponseEntity<>("Not able to find person", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
