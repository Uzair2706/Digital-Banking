package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.dtos.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerOtpServices {

    @Autowired
    ValidationHelper validationHelper;

    @Autowired
    CustomerOtpRepo customerOtpRepo;

    @Transactional
    public void initiateOtp(CustomerOtpDto customerOtpDto) {


        String userName = customerOtpDto.getUserName();
        Customer customer = validationHelper.validateUser(userName);
        String templateId = customerOtpDto.getTemplateId();


        if(templateId==null || templateId.isEmpty()){
           forDefault(customer);
        }
        else if(templateId.equalsIgnoreCase("REGISTRATION")){
//            forRegistration();
        }
        else if (templateId.equalsIgnoreCase("LOGIN")){
//            forLogin();
        }
        else {
//            throw new Exception();
        }

    }

    private void forDefault(Customer customer) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
//        if (customerOtp!=null){
//            customerOtpRepo.delete(customerOtp);
//        }

//        CustomerOtp customerOtp1 = new CustomerOtp();
        customerOtp.setCustomer(customer);

        LocalDateTime now = LocalDateTime.now();
        customerOtp.setCreatedOn(now);

        String otp = generateOtp();
        customerOtp.setOtp(otp);

        customerOtp.setExpiryOn(now.plusMinutes(5));

    }

    @SneakyThrows
    public String generateOtp() {

        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");

        int range = secureRandomGenerator.nextInt(100000,999999);
        return String.valueOf(range);
    }



}
