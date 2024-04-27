
package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import com.attusteste.attusteste.DTO.PersonResponseDTO;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.PersonRepository;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author William Toloto
 */
@Service
public class PersonService {
    
    private PersonRepository repository;
    
    public PersonService(PersonRepository repository){
        this.repository = repository;
    }
    
    
    public List<PersonResponseDTO> createPerson(PersonRequestDTO data){
        Person personData = new Person(data);
        repository.save(personData);
        return listAll();
    }
    
    
    public List<PersonResponseDTO> listAll(){
        List<PersonResponseDTO> personList = repository.findAll().stream().map(PersonResponseDTO::new).toList();    
        return personList;
    }
    
    
    public List<PersonResponseDTO> updatePerson(PersonRequestDTO data){
        Person personData = new Person(data);
        repository.save(personData);
        return listAll();
    }
    
}
