package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.constants.Constants;
import com.mob.casestudy.digitalbanking.dtos.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.exceptions.TemplateIdException;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void initiateOtp(CustomerOtpDto customerOtpDto){
        String userName = customerOtpDto.getUserName();
        Customer customer = validationHelper.validateCustomer(userName,Constants.CUSTOMER_WITH_OTP_NOT_VALID_CODE);
        String templateId = customerOtpDto.getTemplateId();
        CustomerOtp customerOtp = getCustomerOtp(customer,templateId);
        customerOtpRepo.save(customerOtp);
    }

    private CustomerOtp getCustomerOtp(Customer customer,String templateId) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
        String otp = generateOtp();
        return customerOtp.withOtp(otp).withCustomer(customer).withOtpMessage(otpMessage(templateId,otp))
                .withCreatedOn(LocalDateTime.now()).withExpiryOn(LocalDateTime.now().plusMinutes(5));
    }

    private String otpMessage(String templateId, String otp) {
        if(templateId==null || templateId.isEmpty()) return "Your OTP is " + otp;
        else if(templateId.equalsIgnoreCase("REGISTRATION")) return "OTP for the Registration is " + otp;
        else if (templateId.equalsIgnoreCase("LOGIN")) return "OTP for the Login is " + otp;
        else throw new TemplateIdException();
    }

    @SneakyThrows
    private String generateOtp() {
        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG","SUN");
        int range = secureRandomGenerator.nextInt(100000,999999);
        return String.valueOf(range);
    }
}
