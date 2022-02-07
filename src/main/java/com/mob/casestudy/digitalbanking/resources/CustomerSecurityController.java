package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.dtos.CustomerDto;
import com.mob.casestudy.digitalbanking.dtos.InitiateOtpRequest;
import com.mob.casestudy.digitalbanking.dtos.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.services.CustomerOtpServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityQuestionServices;
import com.mob.casestudy.digitalbanking.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequestMapping("/customer-service/")
@RestController
public class CustomerSecurityController {

    //TODO: Try to inject lombok in project
    @Autowired
    CustomerSecurityQuestionServices customerSecurityQuestionServices;
    @Autowired
    CustomerSecurityImageServices customerSecurityImageServices;
    @Autowired
    CustomerOtpServices customerOtpServices;
    @Autowired
    CustomerServices customerServices;

    @GetMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> retrieveQuestionsByUserName(@PathVariable String userName) {
        return ResponseEntity.ok().body(customerSecurityQuestionServices.retrieveQuestions(userName));
    }

    @PutMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> storeCustomerSecurityImagesByUserName(@PathVariable String userName, @Valid @RequestBody CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest) {
        customerSecurityImageServices.storeImages(userName, createCustomerSecurityImageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/client-api/v1/otp/initiate")
    public ResponseEntity<Object> otpInitiationForCustomer(@RequestBody InitiateOtpRequest initiateOtpRequest) {
        customerOtpServices.initiatingOtpForCustomer(initiateOtpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/client-api/v1/customers")
    public ResponseEntity<Object> creatingCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServices.creatingCustomerForApplication(customerDto).getId());
    }
}
