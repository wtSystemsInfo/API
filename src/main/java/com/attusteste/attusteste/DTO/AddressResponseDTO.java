package com.attusteste.attusteste.DTO;

import com.attusteste.attusteste.entity.Address;

/**
 *
 * @author William Toloto
 */
public record AddressResponseDTO(long id, String logradouro, String cep, String numero, String cidade, String uf, boolean principal, Long personId ) {
    public AddressResponseDTO(Address address){
        this(address.getId(), address.getLogradouro(), address.getCep(), address.getNumero(), address.getCidade(), address.getUf(), address.isPrincipal(), address.getPerson().getId());
    }
}
