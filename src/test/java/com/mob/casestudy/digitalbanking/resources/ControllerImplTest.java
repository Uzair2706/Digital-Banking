package com.mob.casestudy.digitalbanking.resources;

import com.digitalbanking.openapi.model.PatchCustomerRequest;
import com.mob.casestudy.digitalbanking.services.*;
import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.InitiateOtpRequest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class ControllerImplTest {

    @Mock
    CustomerSecurityQuestionServices customerSecurityQuestionServices;
    @Mock
    CustomerSecurityImageServices customerSecurityImageServices;
    @Mock
    CustomerOtpServices customerOtpServices;
    @Mock
    CustomerServices customerServices;
    @InjectMocks
    ControllerImpl controller;
    @Mock
    @Autowired
    SecurityQuestionServices securityQuestionServices;

    @Test
    void postCustomers() {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        controller.postCustomers(createCustomerRequest);
        Mockito.verify(customerServices).creatingCustomerForApplication(createCustomerRequest);
    }

    @Test
    void initiateOtp() {
        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest();
        controller.initiateOtp(initiateOtpRequest);
        Mockito.verify(customerOtpServices).initiatingOtpForCustomer(initiateOtpRequest);
    }

    @Test
    void getSecurityQuestionsByUserName() {
        String userName = "";
        controller.getSecurityQuestionsByUserName(userName);
        Mockito.verify(customerSecurityQuestionServices).retrieveQuestions(userName);
    }

    @Test
    void saveSecurityImageById() {
        String userName = "";
        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest();
        controller.saveSecurityImageById(userName,createCustomerSecurityImageRequest);
        Mockito.verify(customerSecurityImageServices).storeImages(userName,createCustomerSecurityImageRequest);
    }

    @Test
    void patchCustomerByUserName(){
        String userName = "";
        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest();
        controller.patchCustomerByUserName(userName,patchCustomerRequest);
        Mockito.verify(customerServices).updateCustomer(userName,patchCustomerRequest);
    }

    @Test
    void getSecurityQuestions() {
        controller.getSecurityQuestions();
        Mockito.verify(securityQuestionServices).getSecurityQuestions();
    }
}