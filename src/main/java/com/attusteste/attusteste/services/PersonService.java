
package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import com.attusteste.attusteste.DTO.PersonResponseDTO;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author William Toloto
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonRepository repository;
    
    
    public Person findPersonById(Long id) throws Exception{
        return repository.findPersonById(id).orElseThrow(() -> new Exception("Pessoa não encontrada!"));
    }
    
    
    public Person savePerson(PersonRequestDTO data) throws Exception{
        
        String nomeAtual = data.nome();
        LocalDate nascimentoAtual = data.nascimento();
        
        Long idatual = findPersonIdByNameAndBirthday(nomeAtual, nascimentoAtual);
        if (idatual != null) {
            throw new Exception("Já existe uma pessoa cadastrada com esses dados. O id do cadastro é : " + idatual );
        }
        
        Person newPerson = new Person(data);
        this.repository.save(newPerson);
        return newPerson;
    }
    
    
    public List<PersonResponseDTO> listAll(){
        List<PersonResponseDTO> personList = repository.findAll().stream().map(PersonResponseDTO::new).toList();    
        return personList;
    }
    
    
    public Person updatePerson(Long personId, PersonRequestDTO data) throws Exception {         
        Person newPerson = findPersonById(personId);
        newPerson.setNome(data.nome());
        newPerson.setNascimento(data.nascimento());        
        repository.save(newPerson);
        return newPerson; 
    }
    
    public Long findPersonIdByNameAndBirthday(String nome, LocalDate nascimento) throws Exception {
        Optional<Person> personOptional = repository.findByNomeAndNascimento(nome, nascimento);
        return personOptional.map(Person::getId).orElse(null);
    }
 
}
