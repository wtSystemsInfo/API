package com.attusteste.attusteste.repository;

import com.attusteste.attusteste.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author William Toloto
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
