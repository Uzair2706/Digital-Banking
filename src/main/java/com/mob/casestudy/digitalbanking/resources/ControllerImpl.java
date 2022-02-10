package com.mob.casestudy.digitalbanking.resources;

import com.digitalbanking.openapi.api.ClientApiApi;
import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.CreateCustomerResponse;
import com.digitalbanking.openapi.model.GetCustomerSecurityQuestionResponse;
import com.digitalbanking.openapi.model.InitiateOtpRequest;
import com.mob.casestudy.digitalbanking.services.CustomerOtpServices;
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
}
