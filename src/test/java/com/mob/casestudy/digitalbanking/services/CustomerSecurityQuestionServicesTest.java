package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.GetCustomerSecurityQuestionResponse;
import com.digitalbanking.openapi.model.SecurityQuestion;
import com.mob.casestudy.digitalbanking.constants.Constants;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class CustomerSecurityQuestionServicesTest {

    @InjectMocks
    CustomerSecurityQuestionServices customerSecurityQuestionServices;
    @Mock
    ValidationHelper validationHelper;

    @Test
    void retrieveQuestions_withCorrectUserName_willReturnCustomerSecurityQuestions() {

        String userName = "UzairKhan2706";
        UUID id = UUID.randomUUID();

        SecurityQuestions securityQuestions = SecurityQuestions.builder().id(id).securityQuestionText("Favorite Show?").build();
        CustomerSecurityQuestions customerSecurityQuestions = CustomerSecurityQuestions.builder().securityQuestionAnswer("The-Office").createdOn(LocalDateTime.now()).build();
        customerSecurityQuestions.setSecurityQuestions(securityQuestions);
        SecurityQuestion securityQuestion = new SecurityQuestion().securityQuestionId(id.toString()).securityQuestionText("Favorite Show?").securityQuestionAnswer("The-Office");
        List<SecurityQuestion> securityQuestionsDtoList = List.of(securityQuestion);
        ResponseEntity<GetCustomerSecurityQuestionResponse> expected = ResponseEntity.ok().body(new GetCustomerSecurityQuestionResponse().securityQuestions(securityQuestionsDtoList));
        Mockito.when(validationHelper.validateQuestionsWithCustomer(userName, Constants.USER_NOT_VALID)).thenReturn(List.of(customerSecurityQuestions));
        ResponseEntity<GetCustomerSecurityQuestionResponse> actual = customerSecurityQuestionServices.retrieveQuestions(userName);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}