package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, String> {

    Optional<Customer> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
