package com.mob.casestudy.digitalbanking.services;

import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityQuestionsDto;
import com.mob.casestudy.digitalbanking.dtos.GetSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.entities.*;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerSecurityQuestionServices {

    @Autowired
    ValidationHelper validationHelper;

    @Transactional
    public GetSecurityQuestionsResponse retrieveQuestions(String userName){

        Customer customer = validationHelper.validateCustomer(userName, USER_NOT_VALID);
        List<CustomerSecurityQuestions> customerSecurityQuestions = customer.getCustomerSecurityQuestions();
        validationHelper.validateQuestions(customerSecurityQuestions);
        List<CustomerSecurityQuestionsDto> customerSecurityQuestionsDtoList = customerSecurityQuestions
                .stream().map(CustomerSecurityQuestions::toDto).toList();
        return new GetSecurityQuestionsResponse(customerSecurityQuestionsDtoList);
    }
}
