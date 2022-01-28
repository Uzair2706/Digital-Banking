package com.mob.casestudy.digitalbanking.helpers;

import com.mob.casestudy.digitalbanking.constants.Constants;
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
import java.util.UUID;

@Component
public class ValidationHelper {

    //TODO: Keep the Component annotation rather than Service
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SecurityImagesRepo securityImagesRepo;

    public Customer validateUser(String userName) {
        Optional<Customer> byUserName = customerRepo.findByUserName(userName);
        if (byUserName.isEmpty()) {
            throw new CustomerNotFoundException(Constants.USER_NOT_VALID,"The username '" + userName + "' is not registered with the system");
        }
        return byUserName.get();
    }

    public void validateQuestions(List<CustomerSecurityQuestions> customerSecurityQuestions) {
        if (customerSecurityQuestions.isEmpty()) {
            throw new CustomerSecurityQuestionsNotFoundException("There are no questions found for this registered user ");
        }
    }


    public Customer validateCustomer(String userName) {
        Optional<Customer> byUserName = customerRepo.findByUserName(userName);
        if (byUserName.isEmpty()) {
            throw new CustomerNotFoundException(Constants.CUSTOMER_NOT_VALID,"The username '" + userName + "' is not registered with the system");
        }
        return byUserName.get();
    }

    public SecurityImages validateImageId(UUID id) {
        Optional<SecurityImages> byId = securityImagesRepo.findById(id);
        if (byId.isEmpty()) {
            throw new SecurityImageIdException("User not validated");
        }
        return byId.get();
    }
}
