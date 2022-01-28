package com.mob.casestudy.digitalbanking.dtos;


public class CustomerSecurityQuestionsDto {

    private String securityQuestionId;
    private String securityQuestionText;
    private String securityQuestionAnswer;

    public CustomerSecurityQuestionsDto(String securityQuestionId, String securityQuestionText, String securityQuestionAnswer) {
        this.securityQuestionId = securityQuestionId;
        this.securityQuestionText = securityQuestionText;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public CustomerSecurityQuestionsDto() {
    }

    public String getSecurityQuestionId() {
        return securityQuestionId;
    }

    public String getSecurityQuestionText() {
        return securityQuestionText;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }
}
