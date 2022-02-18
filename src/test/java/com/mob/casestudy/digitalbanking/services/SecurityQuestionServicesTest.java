package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.GetSecurityQuestionsResponse;
import com.digitalbanking.openapi.model.SecurityQuestion;
import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import com.mob.casestudy.digitalbanking.mappers.SecurityQuestionMapperImpl;
import com.mob.casestudy.digitalbanking.repositories.SecurityQuestionsRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SecurityQuestionServicesTest {

    @InjectMocks
    SecurityQuestionServices securityQuestionServices;
    @Mock
    SecurityQuestionMapperImpl securityQuestionListMapper;
    @Mock
    SecurityQuestionsRepo securityQuestionsRepo;

    @Test
    void getSecurityQuestions() {

        UUID id = UUID.randomUUID();
        SecurityQuestion securityQuestion = new SecurityQuestion().securityQuestionId(id.toString()).securityQuestionText("Favorite Show?").securityQuestionAnswer("The-Office");
        GetSecurityQuestionsResponse getSecurityQuestionsResponse = new GetSecurityQuestionsResponse();
        getSecurityQuestionsResponse.addSecurityQuestionsItem(securityQuestion);
        SecurityQuestions securityQuestions =  SecurityQuestions.builder().id(id).securityQuestionText("Favorite Show?").build();
        List<SecurityQuestions> securityQuestionsList = List.of(securityQuestions);
        Mockito.when(securityQuestionListMapper.mapToDto(0,securityQuestionsList)).thenReturn(getSecurityQuestionsResponse);
        Mockito.when(securityQuestionsRepo.findAll()).thenReturn(securityQuestionsList);
        ResponseEntity<GetSecurityQuestionsResponse> actual = securityQuestionServices.getSecurityQuestions();
        ResponseEntity<GetSecurityQuestionsResponse> expected = ResponseEntity.ok().body(getSecurityQuestionsResponse);
        Mockito.verify(securityQuestionsRepo).findAll();
        Assertions.assertThat(actual).isEqualTo(expected);

    }
}