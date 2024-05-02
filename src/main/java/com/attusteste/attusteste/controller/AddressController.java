package com.attusteste.attusteste.controller;

import com.attusteste.attusteste.DTO.AddressRequestDTO;
import com.attusteste.attusteste.DTO.AddressResponseDTO;
import com.attusteste.attusteste.entity.Address;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.services.AddressService;
import com.attusteste.attusteste.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author William Toloto
 */
@RestController()
@RequestMapping("/addresses")
public class AddressController {
    
    @Autowired
    private AddressService service;
    
    @Autowired PersonService servicePerson;
    
    @PostMapping
     public ResponseEntity<Address> saveAddress(@RequestBody AddressRequestDTO addressRequestDTO, @RequestParam Long personId) throws Exception{
        Person personAddress = servicePerson.findPersonById(personId);
        Address newAddress = service.saveAddress(addressRequestDTO, personAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
     }
     
     
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddress(){
        List<AddressResponseDTO> listAddress =  service.listAll();
        return new ResponseEntity<>(listAddress, HttpStatus.OK);
    } 
    
    
    @GetMapping("/addressById")
    public ResponseEntity<List<AddressResponseDTO>> getAddressByIdPerson(@RequestParam("id") Long idPerson) {        
        List<AddressResponseDTO> listAddress = service.findAllAddressByPerson(idPerson);
        return new ResponseEntity<>(listAddress, HttpStatus.OK);        
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO data) throws Exception{
        Address updatedAddress = service.updateAddress(id, data);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }
    
    @PutMapping("/changeMain/{idOld}")
    public  ResponseEntity<List<AddressResponseDTO>> updateMainAddress(@PathVariable Long idOld, @RequestParam("idNew") Long idNew) throws Exception {
        List<AddressResponseDTO> listAddress = service.updateMainAddress(idOld, idNew);
        return new ResponseEntity<>(listAddress, HttpStatus.OK);        
    }
}
