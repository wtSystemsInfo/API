
package com.attusteste.attusteste.repository;

import com.attusteste.attusteste.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author William Toloto
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findPersonById(Long id);
}
