package com.accela.repository;

import com.accela.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PersonRepository")
public interface PersonRepository extends CrudRepository<Person, Long> {
}
