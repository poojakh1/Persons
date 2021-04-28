package com.accela.controller;

import com.accela.model.Person;
import com.accela.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllUsers() throws Exception {
        Person person = new Person();
        person.setFirstName("Ania");
        person.setLastName("Bruke");

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        when(personService.findAll()).thenReturn(personList);

        mockMvc.perform(get("/persons")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(person.getFirstName()));
    }


    @Test
    public void itShouldReturnCreatedPerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Ania");
        person.setLastName("Bruke");

        when(personService.createPerson(any(Person.class))).thenReturn(ResponseEntity.of(Optional.of(person)));

        mockMvc.perform(post("/persons/add")
                .content(mapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(person.getFirstName()));
    }

    @Test
    public void itShouldReturnUpdatedPerson() throws Exception {
        long id = 1;
        Person updatePerson = new Person();
        updatePerson.setFirstName("Ania");
        updatePerson.setLastName("Bruke");

        when(personService.updatePerson(anyLong(), any(Person.class))).thenReturn(ResponseEntity.of(Optional.of(updatePerson)));

        mockMvc.perform(put("/persons/update/" + id)
                .content(mapper.writeValueAsString(updatePerson))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updatePerson.getFirstName()));
    }

    @Test
    public void itShouldDeleteGivenPerson() throws Exception {
        long id = 1;
        mockMvc.perform(delete("/persons/delete/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

