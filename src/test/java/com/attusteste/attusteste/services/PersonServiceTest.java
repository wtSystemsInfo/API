package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.PersonRepository;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



/**
 *
 * @author William Toloto
 */
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonRepository repository;
    
    @InjectMocks
    private PersonService service;
    
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Should save a new Person when everything is OK")
    public void testSavePerson_Success() throws Exception {
        PersonRequestDTO personRequestDTO = new PersonRequestDTO("William Toloto", LocalDate.of(1991, 9, 19), null);
        when(repository.findByNomeAndNascimento("William Toloto", LocalDate.of(1991, 9, 19))).thenReturn(Optional.empty());
        when(repository.save(any(Person.class))).thenAnswer(invocation -> {
            Person personToSave = invocation.getArgument(0);
            personToSave.setId(1L); 
            return personToSave;
        });
        
        Person savedPerson = service.savePerson(personRequestDTO);
        
        assertNotNull(savedPerson);
        assertEquals("William Toloto", savedPerson.getNome());
        assertEquals(LocalDate.of(1991, 9, 19), savedPerson.getNascimento());
        assertNotNull(savedPerson.getId());
        verify(repository, times(1)).findByNomeAndNascimento("William Toloto", LocalDate.of(1991, 9, 19));
        verify(repository, times(1)).save(any(Person.class));

    }
    
    
    @Test
    @DisplayName("Should throw exception when trying to save Person with empty fields")
    public void testSavePerson_Fail() {
        PersonRequestDTO personRequestDTO = new PersonRequestDTO("William Toloto", LocalDate.of(1991, 9, 19), null);
        when(repository.save(any(Person.class))).thenThrow(new RuntimeException("Existem campos em branco! Preencha corretamente todos os campos!"));
        Exception exception = assertThrows(Exception.class, () -> service.savePerson(personRequestDTO));
        
        
        String expectedErrorMessage = "Existem campos em branco! Preencha corretamente todos os campos!";
        assertTrue(exception.getMessage().contains(expectedErrorMessage));
        
        verify(repository, times(1)).save(any(Person.class));
    }    
    
}
