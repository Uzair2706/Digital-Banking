package com.mob.casestudy.digitalbanking.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GetSecurityQuestionsResponse {


    private List<CustomerSecurityQuestionsDto> securityQuestions;

    public GetSecurityQuestionsResponse(List<CustomerSecurityQuestionsDto> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public List<CustomerSecurityQuestionsDto> getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(List<CustomerSecurityQuestionsDto> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }
}
