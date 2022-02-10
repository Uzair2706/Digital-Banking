package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.PreferredLanguage;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.BadRequestExceptions;
import com.mob.casestudy.digitalbanking.exceptions.NotFoundExceptions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;


@ExtendWith(MockitoExtension.class)
class ValidationHelperTest {

    @InjectMocks
    ValidationHelper validationHelper;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    SecurityImagesRepo securityImagesRepo;

    @Test
    void validateCustomer_shouldValidateCustomerWithTheirName() {
        String byUserName = "UzairKhan2706";
        String code = "CXX-XXX-XXX-00X";
        Customer customer = Customer.builder().build();
        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(customer));
        validationHelper.validateCustomer(byUserName, code);
        Mockito.verify(customerRepo).findByUserName(byUserName);
    }

    @Test
    void validateCustomer_withNoCustomerFound_willThrowAnException() {
        String byUserName = "UzairKhan2706";
        String code = "CXX-XXX-XXX-00X";
        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundExceptions.class, () -> validationHelper.validateCustomer(byUserName, code));
    }


    @Test
    void validateQuestions_withNoQuestions_shouldThrowAnException() {
        String byUserName = "UzairKhan2706";
        String code = "CXX-XXX-XXX-00X";
        Customer customer = Customer.builder().customerSecurityQuestions(new ArrayList<>()).build();
        Mockito.when(customerRepo.findByUserName(byUserName)).thenReturn(Optional.ofNullable(customer));
        Assertions.assertThrows(BadRequestExceptions.class, () -> validationHelper.validateQuestionsWithCustomer(byUserName, code));
    }

    @Test
    void validateImageId_withCorrectId_shouldValidateImageId() {
        UUID byId = UUID.randomUUID();
        String code = "CXX-XXX-XXX-00X";
        SecurityImages securityImages = SecurityImages.builder().securityImageName("Shelby").securityImageUrl("www.shelby_motors.com").build();
        Mockito.when(securityImagesRepo.findById(byId.toString())).thenReturn(Optional.ofNullable(securityImages));
        validationHelper.validateImageId(byId.toString(),code);
        Mockito.verify(securityImagesRepo).findById(byId.toString());
    }

    @Test
    void validateImageId_withNoImageId_shouldThrowAnException() {
        String byId = UUID.randomUUID().toString();
        String code = "CXX-XXX-XXX-00X";
        Mockito.when(securityImagesRepo.findById(byId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundExceptions.class, () -> validationHelper.validateImageId(byId,code));
    }

    @Test
    void validations_withPositiveResponse(){
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("MichaelScott")
                .email("xyz@gmail.com").phoneNumber("7226803020").preferredLanguage(PreferredLanguage.EN);
        Assertions.assertDoesNotThrow(()->validationHelper.validations(createCustomerRequest));
    }

    @Test
    void verifyingUsernameFromDatabase(){
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        Mockito.when(customerRepo.existsByUserName(createCustomerRequest.getUserName())).thenReturn(true);
        Assertions.assertThrows(BadRequestExceptions.class, () -> validationHelper.verifyingUsernameFromDatabase(createCustomerRequest));
    }

    @Test
    void validateCaption(){
        Assertions.assertThrows(BadRequestExceptions.class, () -> validationHelper.validateCaption(null));
        Assertions.assertThrows(BadRequestExceptions.class, () -> validationHelper.validateCaption(""));
        Assertions.assertThrows(BadRequestExceptions.class, () -> validationHelper.validateCaption("UK"));
    }
}