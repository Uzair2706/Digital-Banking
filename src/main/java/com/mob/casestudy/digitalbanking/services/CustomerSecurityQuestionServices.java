package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.mappers.CustomerSecurityQuestionMapperImpl;
import com.digitalbanking.openapi.model.GetCustomerSecurityQuestionResponse;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.digitalbanking.openapi.model.SecurityQuestion;
import com.mob.casestudy.digitalbanking.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerSecurityQuestionServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerSecurityQuestionMapperImpl customerSecurityQuestionMapper;

    @Transactional
    public ResponseEntity<GetCustomerSecurityQuestionResponse> retrieveQuestions(String userName) {
        List<CustomerSecurityQuestions> customerSecurityQuestions = validationHelper.validateQuestionsWithCustomer(userName, USER_NOT_VALID);
        List<SecurityQuestion> securityQuestions = customerSecurityQuestions.stream().map(customerSecurityQuestionMapper::toDto).toList();
        return ResponseEntity.ok().body(new GetCustomerSecurityQuestionResponse().securityQuestions(securityQuestions));
    }
}
