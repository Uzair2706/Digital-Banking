package com.mob.casestudy.digitalbanking.helpers;

import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.PreferredLanguage;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.BadRequestExceptions;
import com.mob.casestudy.digitalbanking.exceptions.NotFoundExceptions;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

import static com.mob.casestudy.digitalbanking.constants.Constants.*;

@Component
public class ValidationHelper {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SecurityImagesRepo securityImagesRepo;

    public Customer validateCustomer(String userName,String code){
        Optional<Customer> customer = customerRepo.findByUserName(userName);
        if (customer.isEmpty()) {
            throw new NotFoundExceptions(code,"The username '" + userName + "' is not registered with the system");
        }
        return customer.get();
    }

    public List<CustomerSecurityQuestions> validateQuestionsWithCustomer(String userName, String code) {
        Customer customer = validateCustomer(userName, code);
        if (customer.getCustomerSecurityQuestions().isEmpty()) {
            throw new BadRequestExceptions(SECURITY_QUESTION_NOT_FOUND_CODE,"There are no questions found for this registered user ");
        }
        return customer.getCustomerSecurityQuestions();
    }

    public SecurityImages validateImageId(String id,String code) {
        Optional<SecurityImages> byId = securityImagesRepo.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundExceptions(code,"Security Image Id not validated");
        }
        return byId.get();
    }

    public void validations(CreateCustomerRequest createCustomerRequest) {
        validateMandatoryFields(createCustomerRequest.getUserName(), REGEX_FOR_USERNAME, USERNAME_INVALID_CODE, USERNAME_INVALID_DESCRIPTION);
        validateMandatoryFields(createCustomerRequest.getEmail(), REGEX_FOR_EMAIL, EMAIL_INVALID_CODE, EMAIL_INVALID_DESCRIPTION);
        validateMandatoryFields(createCustomerRequest.getPhoneNumber(), REGEX_FOR_PHONE_NUMBER, PHONE_NO_INVALID_CODE, PHONE_NO_INVALID_DESCRIPTION);
        validatePreferredLanguage(createCustomerRequest);
    }

    private void validateMandatoryFields(String value, String pattern, String code, String description) {
        if (!value.matches(pattern)) {
            throw new BadRequestExceptions(code, description);
        }
    }

    private void validatePreferredLanguage(CreateCustomerRequest createCustomerRequest) {
        PreferredLanguage.valueOf(createCustomerRequest.getPreferredLanguage().toString());
    }

    public void verifyingUsernameFromDatabase(CreateCustomerRequest createCustomerRequest) {
        if (customerRepo.existsByUserName(createCustomerRequest.getUserName())) {
            throw new BadRequestExceptions(NON_UNIQUE_USERNAME_CODE, NON_UNIQUE_USERNAME_DESCRIPTION);
        }
    }


}
