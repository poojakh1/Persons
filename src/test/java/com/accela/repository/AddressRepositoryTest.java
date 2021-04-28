package com.accela.repository;

import com.accela.model.Address;
import com.accela.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testAddressDb(){
        Person person = new Person();
        person.setId(1);
        person.setFirstName("Test");
        person.setLastName("Java");

        Address address = new Address();
        address.setCity("dublin");
        address.setState("leinster");
        address.setStreet("abc");
        address.setPostalCode("ey345");
        address.setPerson(person);

        assertNotNull(addressRepository);
        Address address1 = addressRepository.save(address);

        assertEquals(address1.getCity(), address.getCity());

        Address addressDb = addressRepository.findById(address1.getId()).get();
        assertNotNull(addressDb);

        assertEquals(addressDb.getCity(),address.getCity());
        assertEquals(addressDb.getPerson(), person);
    }
}
