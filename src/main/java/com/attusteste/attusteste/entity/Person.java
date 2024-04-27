
package com.attusteste.attusteste.entity;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Wiliam Toloto
 */
@Table(name = "person")
@Entity(name = "person")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome;
    private LocalDate nascimento;
    private List<Address> endereco = new ArrayList<>(); 
    
    
    public Person(PersonRequestDTO data){
        this.nome = data.nome();
        this.nascimento = data.nascimento();
        this.endereco = data.endereco();
    }
    
}
