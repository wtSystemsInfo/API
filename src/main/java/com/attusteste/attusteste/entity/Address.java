
package com.attusteste.attusteste.entity;

import com.attusteste.attusteste.DTO.AddressRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author William Toloto
 */
@Table(name = "address")
@Entity(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String uf;
    private boolean principal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id")
    @JsonIgnore
    private Person person;
    
    
    public Address(AddressRequestDTO data, Person person){
        this.logradouro = data.logradouro();
        this.cep = data.cep();
        this.numero = data.numero();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.principal = data.principal();
        this.person = person;
    }
    
}
