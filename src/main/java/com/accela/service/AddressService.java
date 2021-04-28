package com.accela.service;

import com.accela.model.Address;
import com.accela.exception.ResourceNotFoundException;
import com.accela.repository.AddressRepository;
import com.accela.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddressService")
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Address> findAllAddressOfPerson(long personId) {
        return addressRepository.findByPersonId(personId);
    }

    public Address addAddressToPerson(long personId, Address address) {
        return personRepository.findById(personId).map(person -> {
            address.setPerson(person);
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("PersonID " + personId + " not found"));
    }

    public Address updateAddress(long addressId,
                                 Address addressRequest) {
       /*If we pass the personID then check that first to verify if person exists or not
        if (!personRepository.existsById(personId)) {
            throw new ResourceNotFoundException("PersonID " + personId + " not found");
        }*/

        return addressRepository.findById(addressId).map(address -> {
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setStreet(addressRequest.getStreet());
            address.setPostalCode(addressRequest.getPostalCode());
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("AddressId " + addressId + "not found"));
    }


    public ResponseEntity<?> deleteAddress(Long addressId) {
        return addressRepository.findById(addressId).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));
    }
}
