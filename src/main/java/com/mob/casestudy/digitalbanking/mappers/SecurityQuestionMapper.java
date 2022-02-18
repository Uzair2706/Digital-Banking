package com.mob.casestudy.digitalbanking.mappers;

import com.digitalbanking.openapi.model.GetSecurityQuestionsResponse;
import com.digitalbanking.openapi.model.SecurityQuestion;
import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SecurityQuestionMapper {

    List<SecurityQuestion> toDto(List<SecurityQuestions> securityQuestions);
    default String mapToDto(UUID value){
        return value.toString();
    }

    @Mapping(source = "securityQuestionsList",target = "securityQuestions")
    GetSecurityQuestionsResponse mapToDto(Integer value, List<SecurityQuestions> securityQuestionsList);

    @Mapping(source = "securityQuestions.id" ,target = "securityQuestionId")
    @Mapping(source = "securityQuestions.securityQuestionText" ,target = "securityQuestionText")
    SecurityQuestion toDto(SecurityQuestions securityQuestions);
}
