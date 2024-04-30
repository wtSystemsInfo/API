
package com.attusteste.attusteste.repository;


import com.attusteste.attusteste.entity.Person;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author William Toloto
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findPersonById(Long id);
    @EntityGraph(attributePaths = "endereco")
    List<Person> findAll();
    Optional<Person> findByNomeAndNascimento(String nome, LocalDate nascimento);

}
