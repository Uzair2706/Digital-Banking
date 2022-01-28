package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerSecurityImagesRepo extends JpaRepository<CustomerSecurityImages, CustomerSecurityImagesId> {
}
