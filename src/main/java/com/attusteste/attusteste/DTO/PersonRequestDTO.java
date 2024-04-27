package com.attusteste.attusteste.DTO;

import com.attusteste.attusteste.entity.Address;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author William Toloto
 */
public record PersonRequestDTO(String nome, LocalDate nascimento, List<Address> endereco) {
    
}
