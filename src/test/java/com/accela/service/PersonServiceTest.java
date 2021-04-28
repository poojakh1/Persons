package com.accela.service;


import com.accela.exception.ResourceNotFoundException;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    PersonService personService;

    @Test
    public void createPersonTest() {
        Person person = new Person();
        person.setFirstName("Jason");
        person.setLastName("White");
        when(personRepository.save(any(Person.class))).thenReturn(person);
        ResponseEntity<Person> response = personService.createPerson(person);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void getAllPersonsListAndCountTest() {

        Person person1 = new Person();
        person1.setFirstName("Jason");
        person1.setLastName("White");
        Person person2 = new Person();
        person2.setFirstName("Alex");
        person2.setLastName("kolenchiski");
        Person person3 = new Person();
        person3.setFirstName("Steve");
        person3.setLastName("Waugh");

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

        when(personRepository.findAll()).thenReturn(list);

        assertEquals(personService.findAll(), list);

        assertEquals(personService.countPersons(), "Total count of Person " + list.size());
    }


    @Test
    public void updatePersonSuccessTest() {
        Person person = new Person();
        person.setFirstName("Jason");
        person.setLastName("White");

        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        Person personUpdate = new Person();
        person.setFirstName("John");
        person.setLastName("Snow");
        when(personRepository.save(any(Person.class))).thenReturn(personUpdate);

        ResponseEntity<Person> response = personService.updatePerson(1, personUpdate);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getFirstName(), personUpdate.getFirstName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateWillThrowExceptionIfPersonNotExists() {
        Person personUpdate = new Person();
        personUpdate.setFirstName("John");
        personUpdate.setLastName("Snow");
        when(personRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        ResponseEntity<?> response = personService.updatePerson(1, personUpdate);
        assertEquals(response.getBody(), "PersonId 1 not found");
    }


    @Test
    public void deletePersonSuccessTest() {
        Person person = new Person();
        person.setFirstName("Jason");
        person.setLastName("White");

        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        ResponseEntity<?> response = personService.deletePerson(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteWillThrowExceptionIfPersonNotExists() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        ResponseEntity<?> response = personService.deletePerson(1);
        assertEquals(response.getBody(), "PersonId 1 not found");
    }
}
