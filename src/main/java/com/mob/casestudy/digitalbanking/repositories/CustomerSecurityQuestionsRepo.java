package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerSecurityQuestionsRepo extends JpaRepository<CustomerSecurityQuestions, CustomerSecurityQuestionsId> {

}
