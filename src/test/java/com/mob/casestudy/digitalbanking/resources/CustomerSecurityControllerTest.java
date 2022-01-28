package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.dtos.GetSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityQuestionServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class CustomerSecurityControllerTest {


    @InjectMocks
    CustomerSecurityController customerSecurityController;

    @Mock
    CustomerSecurityQuestionServices customerSecurityQuestionServices;

    @Test
    void retrieveQuestionsByUserName() {

        String userName = "UzairKhan2706";

        GetSecurityQuestionsResponse list = new GetSecurityQuestionsResponse();
        ResponseEntity<Object> expected = ResponseEntity.ok().body(list);

        Mockito.when(customerSecurityQuestionServices.retrieveQuestions(userName)).thenReturn(list);

        ResponseEntity<Object> actual = customerSecurityController.retrieveQuestionsByUserName(userName);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

}