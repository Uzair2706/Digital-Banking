package com.mob.casestudy.digitalbanking.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSecurityQuestionsDto {

    private String securityQuestionId;
    private String securityQuestionText;
    private String securityQuestionAnswer;

    public CustomerSecurityQuestionsDto(String securityQuestionId, String securityQuestionText, String securityQuestionAnswer) {
        this.securityQuestionId = securityQuestionId;
        this.securityQuestionText = securityQuestionText;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
