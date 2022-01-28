package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.SecurityImageIdException;
import com.mob.casestudy.digitalbanking.exceptions.CustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exceptions.CustomerSecurityQuestionsNotFoundException;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ValidationHelperTest {

    @InjectMocks
    ValidationHelper validationHelper;

    @Mock
    CustomerRepo customerRepo;

    @Mock
    SecurityImagesRepo securityImagesRepo;

    String byUserName = "UzairKhan2706";

    UUID byId = UUID.randomUUID();

    Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Customer.CustomerStatus.ACTIVE)
            .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

    SecurityImages securityImages = new SecurityImages("Pagani","pagani/here");

    SecurityImages noId;

    Customer noCustomer;

    @Test
    void validateUser_shouldValidateUserWithTheirName(){

          Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(customer));
          validationHelper.validateUser(byUserName);
          Mockito.verify(customerRepo).findByUserName(byUserName);
    }

    @Test
    void validateUser_withNoUserNameFound_shouldThrowAnException() {

        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(noCustomer));
        Assertions.assertThrows(CustomerNotFoundException.class,()-> validationHelper.validateUser(byUserName));
    }

    @Test
    void validateQuestions_withNoQuestions_shouldThrowAnException() {

        List<CustomerSecurityQuestions> customerSecurityQuestions = new ArrayList<>();
        Assertions.assertThrows(CustomerSecurityQuestionsNotFoundException.class,()-> validationHelper.validateQuestions(customerSecurityQuestions));
    }

    @Test
    void validateCustomer_shouldValidateCustomerWithTheirName() {

        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(customer));
        validationHelper.validateUser(byUserName);
        Mockito.verify(customerRepo).findByUserName(byUserName);

    }

    @Test
    void validateCustomer_withNoCustomerFound_willThrowAnException() {

        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(noCustomer));
        Assertions.assertThrows(CustomerNotFoundException.class,()-> validationHelper.validateCustomer(byUserName));
    }

    @Test
    void validateImageId_withCorrectId_shouldValidateImageId() {

        Mockito.when(securityImagesRepo.findById(byId)).thenReturn(Optional.ofNullable(securityImages));
        validationHelper.validateImageId(byId);
        Mockito.verify(securityImagesRepo).findById(byId);
    }

    @Test
    void validateImageId_withNoImageId_shouldThrowAnException(){

        Mockito.when(securityImagesRepo.findById(byId)).thenReturn(Optional.ofNullable(noId));
        Assertions.assertThrows(SecurityImageIdException.class,()-> validationHelper.validateImageId(byId));

    }


}