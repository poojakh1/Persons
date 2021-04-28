package com.accela.controller;

import com.accela.model.Address;
import com.accela.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/persons/{personId}/address")
    public List<Address> getAllAddressesByPersonId(@PathVariable(value = "personId") Long personId) {
        return addressService.findAllAddressOfPerson(personId);
    }


    @PostMapping("/persons/{personId}/address/add")
    public Address addAddress(@PathVariable(value = "personId") Long personId,
                                 @Valid @RequestBody Address address) {
        return addressService.addAddressToPerson(personId, address);
    }

    @PutMapping("/persons/address/update/{addressId}")
    public Address updateAddress(@PathVariable(value = "addressId") Long addressId,
                                 @Valid @RequestBody Address address) {
        return addressService.updateAddress(addressId, address);

    }

    @DeleteMapping("/persons/address/delete/{addressId}")
    public ResponseEntity<?> deleteAddress(
            @PathVariable(value = "addressId") Long addressId) {

        return addressService.deleteAddress(addressId);
    }
}
