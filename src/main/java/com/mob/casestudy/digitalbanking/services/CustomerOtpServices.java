package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityQuestionsDto;
import com.mob.casestudy.digitalbanking.dtos.GetSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerOtpServices {

    @Autowired
    ValidationHelper validationHelper;

    @Transactional
    public GetSecurityQuestionsResponse retrieveQuestions(String userName){

        Customer customer = validationHelper.validateUser(userName);
        List<CustomerSecurityQuestions> customerSecurityQuestions = customer.getCustomerSecurityQuestions();
        validationHelper.validateQuestions(customerSecurityQuestions);
        List<CustomerSecurityQuestionsDto> customerSecurityQuestionsDtoList = customerSecurityQuestions
                .stream().map(CustomerSecurityQuestions::toDto).toList();

        return new GetSecurityQuestionsResponse(customerSecurityQuestionsDtoList);
    }
}
