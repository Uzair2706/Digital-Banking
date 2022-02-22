package com.mob.casestudy.digitalbanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, String> {

    Optional<Customer> findByUserName(String userName);

    boolean existsByUserName(String userName);

    List<Customer> findByUserNameOrId(String userName, String id);
}
