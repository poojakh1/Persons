package com.accela.service;

import com.accela.model.Person;
import com.accela.exception.ResourceNotFoundException;
import com.accela.repository.PersonRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("personService")
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return Lists.newArrayList(personRepository.findAll());
    }

    public String countPersons() {
        List<Person> persons = Lists.newArrayList(personRepository.findAll());
        return "Total count of Person " + persons.size();
    }

    @Transactional
    public ResponseEntity<Person> createPerson(Person person) {
        Person person1 =  personRepository.save(person);
        return ResponseEntity.ok(person1);
    }


    @Transactional
    public ResponseEntity<Person> updatePerson(long personId, Person personData) {
        return personRepository.findById(personId).map(person -> {
            person.setFirstName(personData.getFirstName());
            person.setLastName(personData.getLastName());
            personRepository.save(person);
            return ResponseEntity.ok(person);
        }).orElseThrow(() -> new ResourceNotFoundException("PersonId " + personId + " not found"));
    }

    @Transactional
    public ResponseEntity<?> deletePerson(long personId) {
        return personRepository.findById(personId).map(person -> {
            personRepository.delete(person);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PersonId " + personId + " not found"));
    }

}
