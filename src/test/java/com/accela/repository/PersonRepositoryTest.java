package com.accela.repository;

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
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testPersonDb(){

        Person person = new Person();

        person.setFirstName("Test");
        person.setLastName("Java");

        assertNotNull(personRepository);
        Person person1 = personRepository.save(person);

        assertEquals(person1.getFirstName(), person.getFirstName());

        Person dbPerson = personRepository.findById(person1.getId()).get();
        assertNotNull(dbPerson);

        assertEquals(person.getId(), dbPerson.getId());
        assertEquals(person.getFirstName(), dbPerson.getFirstName());

        long personCount = personRepository.count();
        assertEquals(personCount, 1);

        Iterable<Person> persons = personRepository.findAll();

        int count = 0;

        for(Person p : persons){
            count++;
        }

        assertEquals(count, 1);
    }
}
