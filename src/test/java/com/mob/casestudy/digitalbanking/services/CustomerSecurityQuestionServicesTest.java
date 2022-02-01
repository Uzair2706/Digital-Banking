package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityQuestionsDto;
import com.mob.casestudy.digitalbanking.dtos.GetSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerSecurityQuestionServicesTest {

    @InjectMocks
    CustomerSecurityQuestionServices customerSecurityQuestionServices;

    @Mock
    ValidationHelper validationHelper;

    @Test
    void retrieveQuestions_withCorrectUserName_willReturnCustomerSecurityQuestions() {

        Customer customer = Customer.builder().customerSecurityQuestions(new ArrayList<>()).userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        String userName = "UzairKhan2706";

        SecurityQuestions securityQuestions = new SecurityQuestions(UUID.randomUUID(),"Favorite Show?");
        CustomerSecurityQuestionsId customerSecurityQuestionsId = new CustomerSecurityQuestionsId();

        CustomerSecurityQuestions customerSecurityQuestions =
                new CustomerSecurityQuestions(customerSecurityQuestionsId,"The-Office", LocalDateTime.now());

        customerSecurityQuestions.setSecurityQuestions(securityQuestions);

        List<CustomerSecurityQuestionsDto> securityQuestionsDtoList = List.of(customerSecurityQuestions.toDto());
        GetSecurityQuestionsResponse expected = new GetSecurityQuestionsResponse(securityQuestionsDtoList);

        customer.addCustomerSecurityQuestions(customerSecurityQuestions);

        Mockito.when(validationHelper.validateUser(userName)).thenReturn(customer);

        GetSecurityQuestionsResponse actual = customerSecurityQuestionServices.retrieveQuestions(userName);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}