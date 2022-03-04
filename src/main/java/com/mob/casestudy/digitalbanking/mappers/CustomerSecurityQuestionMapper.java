package com.mob.casestudy.digitalbanking.mappers;

import com.digitalbanking.openapi.model.SecurityQuestion;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerSecurityQuestionMapper {

    @Mapping(source = "customerSecurityQuestions.customerSecurityQuestionsId.securityQuestionId" ,target = "securityQuestionId")
    @Mapping(source = "customerSecurityQuestions.securityQuestions.securityQuestionText" ,target = "securityQuestionText")
    @Mapping(source = "customerSecurityQuestions.securityQuestionAnswer" ,target = "securityQuestionAnswer")
    SecurityQuestion toDto(CustomerSecurityQuestions customerSecurityQuestions);
    default String mapToDto(UUID value){
        return value.toString();
    }

}
