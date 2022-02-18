package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.repositories.SecurityQuestionsRepo;
import com.mob.casestudy.digitalbanking.mappers.SecurityQuestionMapperImpl;
import com.digitalbanking.openapi.model.GetSecurityQuestionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecurityQuestionServices {

    @Autowired
    SecurityQuestionsRepo securityQuestionsRepo;
    @Autowired
    SecurityQuestionMapperImpl securityQuestionListMapper;

    public ResponseEntity<GetSecurityQuestionsResponse> getSecurityQuestions() {
        return ResponseEntity.ok().body(securityQuestionListMapper.mapToDto(0,securityQuestionsRepo.findAll()));
    }
}
