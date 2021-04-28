package com.accela.controller;

import com.accela.model.Person;
import com.accela.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/info")
    public String info() {
        return "The Application is up";
    }


    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/persons/count")
    public String countPersons() {
        return personService.countPersons();
    }

    @PostMapping("/persons/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }


    @PutMapping("/persons/update/{id}")
    public ResponseEntity<Person> updatePerson(@Valid @PathVariable long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/persons/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        return personService.deletePerson(id);
    }
}
