package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.services.CustomerSecurityQuestionServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.services.CustomerOtpServices;
import com.mob.casestudy.digitalbanking.services.CustomerServices;
import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.InitiateOtpRequest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

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
    void testSaveSecurityImageById() {
        String userName = "";
        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest();
        controller.saveSecurityImageById(userName,createCustomerSecurityImageRequest);
        Mockito.verify(customerSecurityImageServices).storeImages(userName,createCustomerSecurityImageRequest);
    }
}