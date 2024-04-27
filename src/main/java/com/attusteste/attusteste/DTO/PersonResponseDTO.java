package com.attusteste.attusteste.DTO;

import com.attusteste.attusteste.entity.Address;
import com.attusteste.attusteste.entity.Person;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author William Toloto
 */
public record PersonResponseDTO(Long id, String nome, LocalDate nascimento, List<Address> endereco) {
    public PersonResponseDTO(Person person){
        this(person.getId(), person.getNome(), person.getNascimento(), person.getEndereco());
    }
}
