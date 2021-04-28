package com.accela.controller;

import com.accela.model.Address;
import com.accela.model.Person;
import com.accela.service.AddressService;
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
@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() {}

    @Test
    public void itShouldReturnAllAddresses() throws Exception {
        long personId = 1;
        Person person = new Person();
        person.setId(personId);
        person.setFirstName("Test");
        person.setLastName("Java");

        Address address = new Address();
        address.setCity("dublin");
        address.setState("leinster");
        address.setStreet("abc");
        address.setPostalCode("ey345");
        address.setPerson(person);


        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        when(addressService.findAllAddressOfPerson(anyLong())).thenReturn(addressList);

        mockMvc.perform(get("/persons/"+personId+"/address")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value(address.getCity()));
    }


    @Test
    public void itShouldReturnCreatedAddress() throws Exception {
        long personId = 1;
        Person person = new Person();
        person.setId(personId);
        person.setFirstName("Test");
        person.setLastName("Java");

        Address address = new Address();
        address.setCity("dublin");
        address.setState("leinster");
        address.setStreet("abc");
        address.setPostalCode("ey345");
        address.setPerson(person);

        when(addressService.addAddressToPerson(anyLong(), any(Address.class))).thenReturn(address);

        mockMvc.perform(post("/persons/"+personId+"/address/add")
                .content(mapper.writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(address.getCity()));
    }

    @Test
    public void itShouldReturnUpdatedAddress() throws Exception {
        long personId = 1, addressId= 123;
        Person person = new Person();
        person.setId(personId);
        person.setFirstName("Test");
        person.setLastName("Java");

        Address address = new Address();
        address.setId(123);
        address.setCity("dublin");
        address.setState("leinster");
        address.setStreet("abc");
        address.setPostalCode("ey345");
        address.setPerson(person);

        when(addressService.updateAddress(anyLong(), any(Address.class))).thenReturn(address);

        mockMvc.perform(put("/persons/address/update/"+addressId)
                .content(mapper.writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(address.getState()));
    }

    @Test
    public void itShouldDeleteGivenAddress() throws Exception {
        long addressId = 1;
        mockMvc.perform(delete("/persons/address/delete/" + addressId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

