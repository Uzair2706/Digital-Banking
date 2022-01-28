package com.mob.casestudy.digitalbanking.dtos;

import java.util.List;

public class GetSecurityQuestionsResponse {


    private List<CustomerSecurityQuestionsDto> securityQuestions;

    public GetSecurityQuestionsResponse() {
    }

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
