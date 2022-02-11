package com.mob.casestudy.digitalbanking.helpers;

import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
import com.mob.casestudy.digitalbanking.exceptions.BadRequestExceptions;
import com.mob.casestudy.digitalbanking.exceptions.NotFoundExceptions;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import com.mob.casestudy.digitalbanking.configurations.RegexValues;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.digitalbanking.openapi.model.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.digitalbanking.openapi.model.PreferredLanguage;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ValidationHelper {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SecurityImagesRepo securityImagesRepo;
    @Autowired
    RegexValues regexValues;

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
        validateMandatoryFields(createCustomerRequest.getUserName(), regexValues.getUserRegex(), USERNAME_INVALID_CODE, USERNAME_INVALID_DESCRIPTION);
        validateMandatoryFields(createCustomerRequest.getEmail(), regexValues.getEmailRegex(), EMAIL_INVALID_CODE, EMAIL_INVALID_DESCRIPTION);
        validateMandatoryFields(createCustomerRequest.getPhoneNumber(), regexValues.getPhoneRegex(), PHONE_NO_INVALID_CODE, PHONE_NO_INVALID_DESCRIPTION);
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

    public void validateCaption(String caption) {
        if (Objects.isNull(caption))
            throw new BadRequestExceptions(CAPTION_NOT_VALID, CAPTION_NOT_NULL_DESCRIPTION);
        if (caption.isEmpty())
            throw new BadRequestExceptions(CAPTION_NOT_VALID, CAPTION_NOT_EMPTY_DESCRIPTION);
        if (caption.length() <= 3)
            throw new BadRequestExceptions(CAPTION_NOT_VALID, CAPTION_SIZE_NOT_VALID_DESCRIPTION);
    }


}
