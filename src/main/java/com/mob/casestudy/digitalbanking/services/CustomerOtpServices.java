package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.InitiateOtpRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.exceptions.BadRequestExceptions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class CustomerOtpServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerOtpRepo customerOtpRepo;

    @Transactional
    public ResponseEntity<Void> initiatingOtpForCustomer(InitiateOtpRequest initiateOtpRequest) {
        String userName = initiateOtpRequest.getUserName();
        Customer customer = validationHelper.validateCustomer(userName, CUSTOMER_WITH_INVALID_CODE);
        String templateId = initiateOtpRequest.getTemplateId();
        CustomerOtp customerOtp = getCustomerOtp(customer, templateId);
        customerOtpRepo.save(customerOtp);
        return ResponseEntity.ok().build();
    }

    private CustomerOtp getCustomerOtp(Customer customer, String templateId) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
        String otp = generateOtp();
        return customerOtp.withOtp(otp).withCustomer(customer).withOtpMessage(otpMessage(templateId, otp))
                .withCreatedOn(LocalDateTime.now()).withExpiryOn(LocalDateTime.now().plusMinutes(5));
    }

    private String otpMessage(String templateId, String otp) {
        if (templateId == null || templateId.isEmpty()) return DEFAULT_OTP + otp;
        else if (templateId.equalsIgnoreCase("REGISTRATION")) return REG_OTP + otp;
        else if (templateId.equalsIgnoreCase("LOGIN")) return LOGIN_OTP + otp;
        else throw new BadRequestExceptions(TEMPLATE_ID_NOT_VALID,TEMPLATE_ID_NOT_VALID_DESCRIPTION);
    }

    @SneakyThrows
    private String generateOtp() {
        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
        int range = secureRandomGenerator.nextInt(100000, 999999);
        return String.valueOf(range);
    }
}
