package com.attusteste.attusteste.repository;

import com.attusteste.attusteste.entity.Address;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author William Toloto
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
   List<Address> findAllByPersonId(Long personId);
   Optional<Address> findAddressById(Long id);
}
