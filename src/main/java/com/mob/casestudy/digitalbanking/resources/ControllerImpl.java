package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digitalbanking.openapi.api.ClientApiApi;
import org.springframework.http.ResponseEntity;
import com.digitalbanking.openapi.model.*;

@RestController
@RequestMapping("/customer-service/")
public class ControllerImpl implements ClientApiApi {

    @Autowired
    CustomerServices customerServices;
    @Autowired
    CustomerOtpServices customerOtpServices;
    @Autowired
    CustomerSecurityQuestionServices customerSecurityQuestionServices;
    @Autowired
    CustomerSecurityImageServices customerSecurityImageServices;
    @Autowired
    SecurityQuestionServices securityQuestionServices;

    @Override
    public ResponseEntity<CreateCustomerResponse> postCustomers(CreateCustomerRequest createCustomerRequest) {
        return customerServices.creatingCustomerForApplication(createCustomerRequest);
    }

    @Override
    public ResponseEntity<Void> initiateOtp(InitiateOtpRequest initiateOtpRequest) {
        return customerOtpServices.initiatingOtpForCustomer(initiateOtpRequest);
    }

    @Override
    public ResponseEntity<GetCustomerSecurityQuestionResponse> getSecurityQuestionsByUserName(String username) {
        return customerSecurityQuestionServices.retrieveQuestions(username);
    }

    @Override
    public ResponseEntity<Void> saveSecurityImageById(String username, CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest) {
        return customerSecurityImageServices.storeImages(username, createCustomerSecurityImageRequest);
    }

    @Override
    public ResponseEntity<Void> patchCustomerByUserName(String username, PatchCustomerRequest patchCustomerRequest) {
        return customerServices.updateCustomer(username, patchCustomerRequest);
    }

    @Override
    public ResponseEntity<GetSecurityQuestionsResponse> getSecurityQuestions() {
        return securityQuestionServices.getSecurityQuestions();
    }

    @Override
    public ResponseEntity<GetCustomerResponse> getCustomers(String id, String userName) {
        return customerServices.getCustomers(id, userName);
    }
}
