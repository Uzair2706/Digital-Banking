package com.mob.casestudy.digitalbanking.repositories;

import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface SecurityQuestionsRepo extends JpaRepository<SecurityQuestions, UUID> {
}
