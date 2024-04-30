package com.attusteste.attusteste.DTO;

/**
 *
 * @author William Toloto
 */
public record AddressRequestDTO(String logradouro, String cep, String numero, String cidade, String uf, boolean principal, Long personId) {
    
}
