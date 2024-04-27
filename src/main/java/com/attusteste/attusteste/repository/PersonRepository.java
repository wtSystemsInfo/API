
package com.attusteste.attusteste.repository;

import com.attusteste.attusteste.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author William Toloto
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
