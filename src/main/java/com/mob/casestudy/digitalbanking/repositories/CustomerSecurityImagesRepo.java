package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerSecurityImagesRepo extends JpaRepository<CustomerSecurityImages, CustomerSecurityImagesId> {

    @Query("select csi from CustomerSecurityImages csi join csi.customer c where c.userName like :userName")
    Optional<CustomerSecurityImages> findByUserName(String userName);


}
