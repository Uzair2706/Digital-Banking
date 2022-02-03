package com.mob.casestudy.digitalbanking.helpers;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.SecurityImageIdException;
import com.mob.casestudy.digitalbanking.exceptions.CustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exceptions.CustomerSecurityQuestionsNotFoundException;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidationHelper {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SecurityImagesRepo securityImagesRepo;

    public Customer validateCustomer(String userName,String code){
        Optional<Customer> customer = customerRepo.findByUserName(userName);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(code,"The username '" + userName + "' is not registered with the system");
        }
        return customer.get();
    }

    public void validateQuestions(List<CustomerSecurityQuestions> customerSecurityQuestions) {
        if (customerSecurityQuestions.isEmpty()) {
            throw new CustomerSecurityQuestionsNotFoundException("There are no questions found for this registered user ");
        }
    }

    public SecurityImages validateImageId(String id) {
        Optional<SecurityImages> byId = securityImagesRepo.findById(id);
        if (!byId.isPresent()) {
            throw new SecurityImageIdException("Security Image Id not validated");
        }
        return byId.get();
    }
}
