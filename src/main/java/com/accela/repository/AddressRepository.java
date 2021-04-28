package com.accela.repository;

import com.accela.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("AddressRepository")
public interface AddressRepository extends CrudRepository<Address, Long> {


    List<Address> findByPersonId(long personId);
    Optional<Address> findById(long id);
}
