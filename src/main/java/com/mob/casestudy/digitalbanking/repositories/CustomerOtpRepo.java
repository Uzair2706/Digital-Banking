package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerOtpRepo extends JpaRepository<CustomerOtp,CustomerOtpId> {

}
