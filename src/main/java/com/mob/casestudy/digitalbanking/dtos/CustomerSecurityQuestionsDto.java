package com.mob.casestudy.digitalbanking.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSecurityQuestionsDto {

    private String securityQuestionId;
    private String securityQuestionText;
    private String securityQuestionAnswer;
}
