package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityQuestionsDto;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CustomerSecurityQuestions {

    @EmbeddedId
    private CustomerSecurityQuestionsId customerSecurityQuestionsId = new CustomerSecurityQuestionsId();

    @Column(length = 50)
    private String securityQuestionAnswer;

    @Column(length = 50)
    private LocalDateTime createdOn;

    @ManyToOne
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("securityQuestionId")
    private SecurityQuestions securityQuestions;

    public CustomerSecurityQuestions() {
    }

    public CustomerSecurityQuestions(CustomerSecurityQuestionsId customerSecurityQuestionsId, String securityQuestionAnswer, LocalDateTime createdOn) {
        this.customerSecurityQuestionsId = customerSecurityQuestionsId;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.createdOn = createdOn;
    }

    public CustomerSecurityQuestions(String securityQuestionAnswer, LocalDateTime createdOn) {
        this.customerSecurityQuestionsId = new CustomerSecurityQuestionsId();
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.createdOn = createdOn;
    }

    public CustomerSecurityQuestionsId getCustomerSecurityQuestionsId() {
        return customerSecurityQuestionsId;
    }

    public void setCustomerSecurityQuestionsId(CustomerSecurityQuestionsId customerSecurityQuestionsId) {
        this.customerSecurityQuestionsId = customerSecurityQuestionsId;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SecurityQuestions getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(SecurityQuestions securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public CustomerSecurityQuestionsDto toDto(){
        return new CustomerSecurityQuestionsDto(securityQuestions.getId().toString(),securityQuestions.getSecurityQuestionText(),securityQuestionAnswer);
    }
}
