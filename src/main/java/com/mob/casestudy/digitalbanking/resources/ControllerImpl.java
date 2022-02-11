package com.mob.casestudy.digitalbanking.resources;

import com.digitalbanking.openapi.api.ClientApiApi;
import com.digitalbanking.openapi.model.*;
import com.mob.casestudy.digitalbanking.services.CustomerOtpServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityQuestionServices;
import com.mob.casestudy.digitalbanking.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return customerSecurityImageServices.storeImages(username,createCustomerSecurityImageRequest);
    }

    @Override
    public ResponseEntity<Void> patchCustomerByUserName(String username, PatchCustomerRequest patchCustomerRequest) {
        return customerServices.updateCustomer(username,patchCustomerRequest);
    }
}
