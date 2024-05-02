package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.AddressRequestDTO;
import com.attusteste.attusteste.entity.Address;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.AddressRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
 * @author Wiliam Toloto
 */
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    
    @Mock
    private AddressRepository repository;
    
    @InjectMocks
    private AddressService service;
  
    
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);        
    }
    
    @Test
    @DisplayName("Should save a new Address when everything is OK")
    public void testSaveAddress_Success() throws Exception {
        AddressRequestDTO adress = new AddressRequestDTO("Rua Brasil", "87013060", "555", "Maringa", "PR", true, null);
        Person person = new Person();
        
        Address savedAddress = new Address();
        
        savedAddress.setLogradouro(adress.logradouro());
        savedAddress.setCep(adress.cep());
        savedAddress.setNumero(adress.numero());
        savedAddress.setCidade(adress.cidade());
        savedAddress.setUf(adress.uf());
        savedAddress.setPrincipal(adress.principal());
        savedAddress.setPerson(person);
        
        when(repository.save(any(Address.class))).thenReturn(savedAddress);
        
        Address result = service.saveAddress(adress, person);
        
        assertNotNull(result);
        assertEquals(savedAddress, result);
        assertEquals(adress.logradouro(), result.getLogradouro());
        assertEquals(adress.cep(), result.getCep());
        assertEquals(adress.numero(), result.getNumero());
        assertEquals(adress.cidade(), result.getCidade());
        assertEquals(adress.uf(), result.getUf());
        assertEquals(adress.principal(), result.isPrincipal());
        verify(repository, times(1)).save(any(Address.class));

    }
    
    @Test
    @DisplayName("Should throw exception when some address field is empty or all of them")
    public void testSaveAddress_Fail() throws Exception {
        AddressRequestDTO adress = new AddressRequestDTO("", "87013060", "555", "Maringa", "PR", true, null);
        Person person = new Person();
        
        Address savedAddress = new Address();
        
        savedAddress.setLogradouro(adress.logradouro());
        savedAddress.setCep(adress.cep());
        savedAddress.setNumero(adress.numero());
        savedAddress.setCidade(adress.cidade());
        savedAddress.setUf(adress.uf());
        savedAddress.setPrincipal(adress.principal());
        savedAddress.setPerson(person);
        
        assertThrows(Exception.class, () -> service.saveAddress(adress, person));
        
    }
}
