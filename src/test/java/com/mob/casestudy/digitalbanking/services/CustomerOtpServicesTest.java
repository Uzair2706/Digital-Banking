package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import com.mob.casestudy.digitalbanking.configurations.OtpConstant;
import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.digitalbanking.openapi.model.InitiateOtpRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import static com.mob.casestudy.digitalbanking.constants.Constants.*;

@ExtendWith(MockitoExtension.class)
class CustomerOtpServicesTest {

    @InjectMocks
    CustomerOtpServices customerOtpServices;
    @Mock
    ValidationHelper validationHelper;
    @Mock
    CustomerOtpRepo customerOtpRepo;
    @Mock
    OtpConstant otpConstant;

    @Test
    void initiateOtp_forCustomerWithValidInput() {

        String userName = "UzairKhan2706";
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).build();
        Customer customer = Customer.builder().customerOtp(customerOtp).build();
        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest().userName(userName);
        Mockito.when(otpConstant.getOrigin()).thenReturn(100000);
        Mockito.when(otpConstant.getBound()).thenReturn(999999);
        Mockito.when(validationHelper.validateCustomer(userName, CUSTOMER_WITH_INVALID_CODE)).thenReturn(customer);
        ResponseEntity<Void> actual = customerOtpServices.initiatingOtpForCustomer(initiateOtpRequest);
        Mockito.verify(validationHelper).validateCustomer(userName, CUSTOMER_WITH_INVALID_CODE);
        Mockito.verify(customerOtpRepo).save(Mockito.any());
        ResponseEntity<Object> expected = ResponseEntity.ok().build();
        Assertions.assertEquals(expected,actual);
    }
}