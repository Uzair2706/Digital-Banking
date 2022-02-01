package com.mob.casestudy.digitalbanking.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CustomerSecurityQuestionsId implements Serializable {

    private String customerId;

    private UUID securityQuestionId;

    public CustomerSecurityQuestionsId() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public UUID getSecurityQuestionId() {
        return securityQuestionId;
    }

    public CustomerSecurityQuestionsId(String customerId, UUID securityQuestionId) {
        this.customerId = customerId;
        this.securityQuestionId = securityQuestionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSecurityQuestionsId that = (CustomerSecurityQuestionsId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(securityQuestionId, that.securityQuestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, securityQuestionId);
    }
}
