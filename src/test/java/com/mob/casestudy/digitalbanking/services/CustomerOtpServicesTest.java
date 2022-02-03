package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.constants.Constants;
import com.mob.casestudy.digitalbanking.dtos.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class CustomerOtpServicesTest {

    @InjectMocks
    CustomerOtpServices customerOtpServices;

    @Mock
    ValidationHelper validationHelper;

    @Mock
    CustomerOtpRepo customerOtpRepo;

    @Test
    void initiateOtp() {

        String userName = "UzairKhan2706";
        String templateId = "REGISTRATION";

        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("Otp Generated").otp("786930").otpRetries(0).expiryOn(LocalDateTime.now()).createdOn(LocalDateTime.now()).build();

        Customer customer = Customer.builder().customerOtp(customerOtp).userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        CustomerOtpDto customerOtpDto = CustomerOtpDto.builder().templateId(templateId).userName(userName).build();

        Mockito.when(validationHelper.validateCustomer(Constants.CUSTOMER_WITH_OTP_NOT_VALID_CODE,userName)).thenReturn(customer);
        customerOtpServices.initiateOtp(customerOtpDto);
        Mockito.verify(validationHelper).validateCustomer(Constants.CUSTOMER_WITH_OTP_NOT_VALID_CODE,userName);
        Mockito.verify(customerOtpRepo).save(Mockito.any());

    }
}