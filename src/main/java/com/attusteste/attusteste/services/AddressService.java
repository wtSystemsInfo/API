package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.AddressRequestDTO;
import com.attusteste.attusteste.DTO.AddressResponseDTO;
import com.attusteste.attusteste.entity.Address;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author William Toloto
 */
@Service
public class AddressService {
    
    @Autowired
    private AddressRepository repository;
    
    
    public Address saveAddress(AddressRequestDTO address, Person person) throws Exception {
        
        
        if(!noMainAddress(person, address)){
            throw new Exception("Essa pessoa já tem um endereço principal!");
        }
        
        Address newAddress = new Address();
        newAddress.setLogradouro(address.logradouro());
        newAddress.setCep(address.cep());
        newAddress.setNumero(address.numero());
        newAddress.setCidade(address.cidade());
        newAddress.setUf(address.uf());
        newAddress.setPrincipal(address.principal());
        newAddress.setPerson(person);
        
        
        if(!validateAddress(newAddress)){
             throw new Exception("Exitem campos em branco no endereço! Preencha todos os campos!");
        }
        
        repository.save(newAddress);
        return newAddress;
    }
    
    
    public List<AddressResponseDTO> listAll(){
        List<AddressResponseDTO> addressList = repository.findAll().stream().map(AddressResponseDTO::new).toList();
        return addressList;
    }
    
    public List<AddressResponseDTO> findAllAddressByPerson(Long idPerson){   
        List<AddressResponseDTO> addressList = repository.findAllByPersonId(idPerson).stream().map(AddressResponseDTO::new).toList();
        return addressList;       
    }
    
    public Address updateAddress(Long idAddress, AddressRequestDTO newAddress) throws Exception {
        Address actualAddress = repository.findAddressById(idAddress).orElseThrow(() -> new Exception("Endereço não encontrado!"));
                
        actualAddress.setLogradouro(newAddress.logradouro());
        actualAddress.setCep(newAddress.cep());
        actualAddress.setNumero(newAddress.numero());
        actualAddress.setCidade(newAddress.cidade());
        actualAddress.setUf(newAddress.uf());
        actualAddress.setPrincipal(newAddress.principal());
        
        
        if(!validateAddress(actualAddress)){
             throw new Exception("Exitem campos em branco no endereço! Preencha todos os campos!");
        }
        
        repository.save(actualAddress);
        
        return actualAddress;
    }
    
    
    public List<AddressResponseDTO> updateMainAddress(Long idAddressOld, Long idAddressNew) throws Exception {
        Address oldAddress = repository.findAddressById(idAddressOld).orElseThrow(() -> new Exception("Endereço não encontrado!"));
        Address newAddress = repository.findAddressById(idAddressNew).orElseThrow(() -> new Exception("Endereço não encontrado!")); 
        Long idPerson = oldAddress.getPerson().getId();
        
        if (!oldAddress.getPerson().equals(newAddress.getPerson())) {
            throw new Exception("O novo endereço não pertence à mesma pessoa do endereço antigo!");
        }
        
        
        oldAddress.setPrincipal(false);
        newAddress.setPrincipal(true);
        
        repository.save(oldAddress);
        repository.save(newAddress);
        
        return findAllAddressByPerson(idPerson);
    }
    
    
    
    public boolean validateAddress(Address address){
        if(address.getLogradouro() == null || address.getLogradouro().isBlank()){
            return false;
        }
        if(address.getCep() == null || address.getCep().isBlank()){
            return false;
        }
        if(address.getNumero() == null || address.getNumero().isBlank()){
            return false;
        }
        if(address.getCidade() == null || address.getCidade().isBlank()){
            return false;
        }
        if(address.getUf() == null || address.getUf().isBlank()){
            return false;
        }
        
        return true;
    }
    
    public boolean noMainAddress(Person person, AddressRequestDTO address){
        boolean mainAddress = person.getEndereco().stream().anyMatch(Address::isPrincipal);
        if(mainAddress && address.principal()){
            return false;
        }
        return true; 
    }
    
}
