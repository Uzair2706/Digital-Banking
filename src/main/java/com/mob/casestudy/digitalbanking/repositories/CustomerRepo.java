package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUserName(String userName);

}
